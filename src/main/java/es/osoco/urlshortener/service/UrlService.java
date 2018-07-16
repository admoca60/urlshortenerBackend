package es.osoco.urlshortener.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.osoco.urlshortener.api.utils.ResponseWrapper;
import es.osoco.urlshortener.entity.Url;
import es.osoco.urlshortener.model.UrlDTO;
import es.osoco.urlshortener.repository.UrlRepository;
import es.osoco.urlshortener.utils.Base62;
import es.osoco.urlshortener.utils.ErrorDesc;
import es.osoco.urlshortener.utils.ResponseEnum;
import es.osoco.urlshortener.utils.UrlShortenerConstants;
import es.osoco.urlshortener.utils.Utils;

/**
 * @author amolinca
 *
 */
@Service
public class UrlService {
	/**
	 * Attribute for storing the field urlRepository.
	 */
	@Autowired
	private UrlRepository urlRepository;

	/**
	 * Method that returns the value of the field urlRepository.
	 *
	 * @return the urlRepository
	 */
	public UrlRepository getUrlRepository() {
		return urlRepository;
	}

	/**
	 * Sets the value of the field urlRepository. 
	 *
	 * @param urlRepository the urlRepository to set
	 */
	public void setUrlRepository(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}
	
	/**
	 * Method that returns the LongUrl from the hashCode. It also validates the url's expiration
	 * @param hashCode String.
	 * @return ResponseWrapper object.
	 */
	public ResponseWrapper<UrlDTO> getUrlByHashCode(String hashCode){
		ResponseWrapper<UrlDTO> result = new ResponseWrapper<UrlDTO>();
		
		//Validate the request param value
		if(!Utils.checkValidHashCode(hashCode)){
			result.setStatus(false);
			result.setErrorDesc(new ErrorDesc(ResponseEnum.INVALIDREQUEST));
			return result;
		}
		
		
		Url urlRecord = this.urlRepository.findByHashCode(hashCode);
		
		
		
		
		if(urlRecord!=null){
			if(urlRecord.isExpired()){
				result.setStatus(false);
				result.setErrorDesc(new ErrorDesc(ResponseEnum.EXPIREDURL));
			}else{
				result.setStatus(true);
				result.setData(new UrlDTO(urlRecord.getHashCode(),urlRecord.getUrlLong()));
			}
		}else{
			result.setStatus(false);
			result.setErrorDesc(new ErrorDesc(ResponseEnum.URLNOTFOUND));
		}
		return result;
			
	}
	
	/**
	 * Method that returns the whole list of urls which are allocated.
	 * @return ResponseWrapper object.
	 */
	public ResponseWrapper<List<UrlDTO>> getUrlList(){
		ResponseWrapper<List<UrlDTO>> result = new ResponseWrapper<List<UrlDTO>>();
		
		List<Url> urlList = this.urlRepository.findByAllocated(true);
		result.setStatus(true);
		result.setData(createListOfUrlDTO(urlList));
		return result;
			
	}
	
	private List<UrlDTO> createListOfUrlDTO(List<Url> listUrl){
		List<UrlDTO> listResult = new ArrayList<UrlDTO>(listUrl.size());
		for(Url u : listUrl){
			listResult.add(new UrlDTO(u.getHashCode(),u.getUrlLong()));
		}
		
		return listResult;
	}

	/**
	 * Service method that creates the new entry in the table, 
	 * either in an old record or in a new one.
	 * @param urlsModel
	 * @return ResponseWrapper object
	 */
	@Transactional
	public ResponseWrapper<UrlDTO> addNewUrl(UrlDTO urlsModel) {
		ResponseWrapper<UrlDTO> result = new ResponseWrapper<UrlDTO>();
		
		//Validate the request param values
		if(urlsModel==null 
				|| !StringUtils.isEmpty(urlsModel.getHashCode())
				|| StringUtils.isEmpty(urlsModel.getUrlLong())
				|| urlsModel.getUrlLong().length()>UrlShortenerConstants.URL_LONG_MAX_LENGTH){
			result.setStatus(false);
			result.setErrorDesc(new ErrorDesc(ResponseEnum.INVALIDREQUEST));
			return result;
		}
		
		//Get next slot
		Url url = new Url();
		url.setUrlLong(urlsModel.getUrlLong());
		url.setAllocated(true);
		url.setExpired(false);
		url = this.urlRepository.save(url);
		
		//Check that ID is not out of boundary
		if(url.getId()>UrlShortenerConstants.URL_ID_MAX_VALUE){
			//Delete the record and return that the space was run out
			this.urlRepository.delete(url);
			result.setStatus(false);
			result.setErrorDesc(new ErrorDesc(ResponseEnum.NOTENOUGHTSPACE));
			return result;
			
			//FIXME in this point should be coded the policy to reuse any unused record (taking into account the concurrency)
			
		}
		
		url.setHashCode(Base62.encode(url.getId()));
		this.urlRepository.save(url);
		
		result.setStatus(true);
		result.setData(new UrlDTO(url.getHashCode(),url.getUrlLong()));
		return result;		
	}
	
	/**
	 * Method that mark as unused an entry in the table
	 * @param urlsModel UrlsModel
	 * @return UrlResponse for URL
	 */
	@Transactional
	public ResponseWrapper<UrlDTO> logicalDeleteUrl(UrlDTO urlsModel){
		ResponseWrapper<UrlDTO> result = new ResponseWrapper<UrlDTO>();
		
		//Validate the request param values
		if(urlsModel==null 
				|| StringUtils.isEmpty(urlsModel.getHashCode())
				|| !Utils.checkValidHashCode(urlsModel.getHashCode())){
			result.setStatus(false);
			result.setErrorDesc(new ErrorDesc(ResponseEnum.INVALIDREQUEST));
			return result;
		}
		
		//Get the object from DB
		Url url = this.urlRepository.findByHashCode(urlsModel.getHashCode());
		if(url==null){
			result.setStatus(false);
			result.setErrorDesc(new ErrorDesc(ResponseEnum.URLNOTFOUND));
			return result;
		}
		url.setAllocated(false);
		url.setExpired(true);
		
		url = this.urlRepository.save(url);
		result.setStatus(true);
		result.setData(new UrlDTO(url.getHashCode(),url.getUrlLong()));
		return result;
	}
	
}
