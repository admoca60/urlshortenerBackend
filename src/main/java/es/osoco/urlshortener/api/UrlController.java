package es.osoco.urlshortener.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.osoco.urlshortener.api.utils.ResponseWrapper;
import es.osoco.urlshortener.model.UrlDTO;
import es.osoco.urlshortener.service.UrlService;

@RestController
@RequestMapping("/url")
public class UrlController {
	/**
	 * Attribute for storing the field urlService.
	 */
	@Autowired
	private UrlService urlService;
	
	
	@GetMapping("{hashCode}")
	public ResponseWrapper<UrlDTO> getLongUrl(@PathVariable("hashCode") String hashCode){
		return urlService.getUrlByHashCode(hashCode);
	}
	
	@GetMapping("/")
	public ResponseWrapper<List<UrlDTO>> getUrlList(){
		return urlService.getUrlList();
	}
	@PostMapping("/add")
	public ResponseWrapper<UrlDTO> addNewUrl(@RequestBody UrlDTO urlsModel){
		return urlService.addNewUrl(urlsModel);
	}
	
	@DeleteMapping("/delete/{hashCode}")
	public ResponseWrapper<UrlDTO> deleteUrl(@PathVariable("hashCode") String hashCode){
		UrlDTO urlDTO = new UrlDTO();
		urlDTO.setHashCode(hashCode);
		return urlService.logicalDeleteUrl(urlDTO);
	}
}
