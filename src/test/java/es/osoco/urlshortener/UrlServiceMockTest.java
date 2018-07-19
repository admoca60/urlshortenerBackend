package es.osoco.urlshortener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import es.osoco.urlshortener.api.utils.ResponseWrapper;
import es.osoco.urlshortener.entity.Url;
import es.osoco.urlshortener.model.UrlDTO;
import es.osoco.urlshortener.repository.UrlRepository;
import es.osoco.urlshortener.service.UrlService;
import es.osoco.urlshortener.utils.ResponseEnum;
import es.osoco.urlshortener.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceMockTest {

	@Mock
	UrlRepository urlRepository;
	
	@InjectMocks
	UrlService urlService;
	
	Url returnedUrlAA;
	Url expiredUrl;
	
	@Before
	public void initialisation(){
		returnedUrlAA = new Url();
		returnedUrlAA.setHashCode("aa");
		returnedUrlAA.setUrlLong("http://www.google.es");
		
		expiredUrl = new Url();
		expiredUrl.setHashCode("ee");
		expiredUrl.setUrlLong("urlTest");
		expiredUrl.setExpired(true);
		
		when(urlRepository.findByHashCode(returnedUrlAA.getHashCode())).thenReturn(returnedUrlAA);
		when(urlRepository.findByHashCode(expiredUrl.getHashCode())).thenReturn(expiredUrl);
	}
	
	@Test
	public void initialTest(){
		//Invocation of method
		ResponseWrapper<UrlDTO> dataReturned = this.urlService.getUrlByHashCode(returnedUrlAA.getHashCode());
		
		//Verifications for the invoked method
		verify(urlRepository).findByHashCode(returnedUrlAA.getHashCode());
		assertEquals(returnedUrlAA.getHashCode(),dataReturned.getData().getHashCode());
		assertEquals(returnedUrlAA.getUrlLong(),dataReturned.getData().getUrlLong());
	}
	@Test
	public void invalidHashCodeTest(){
		//Invocation of method
		ResponseWrapper<UrlDTO> dataReturned = this.urlService.getUrlByHashCode("");
		
		//Verifications for the invoked method
		verifyZeroInteractions(urlRepository);
		assertEquals(false,dataReturned.isStatus());
		assertEquals(ResponseEnum.INVALIDREQUEST.getErrorNumber(),dataReturned.getErrorDesc().getErrorCode());
		assertEquals(ResponseEnum.INVALIDREQUEST.getErrorMessage(),dataReturned.getErrorDesc().getErrorDesc());
	}
	@Test 
	public void checkUtilsTest(){
		assertEquals(false,Utils.checkValidHashCode(""));
		assertEquals(false,Utils.checkValidHashCode("-"));
		assertEquals(false,Utils.checkValidHashCode("?"));
		assertEquals(false,Utils.checkValidHashCode("/"));
		assertEquals(false,Utils.checkValidHashCode("$"));
		assertEquals(false,Utils.checkValidHashCode("aaaaaaaaa"));
		
		assertEquals(true,Utils.checkValidHashCode("1"));
		assertEquals(true,Utils.checkValidHashCode("a"));
		assertEquals(true,Utils.checkValidHashCode("A"));
		assertEquals(true,Utils.checkValidHashCode("1a"));
		assertEquals(true,Utils.checkValidHashCode("1aA"));
		assertEquals(true,Utils.checkValidHashCode("aaaDaaa1"));
		
	}
	
	@Test
	public void urlNotFoundTest(){
		//Invocation of method
		ResponseWrapper<UrlDTO> dataReturned = this.urlService.getUrlByHashCode("fsd");
		//Verifications for the invoked method
		verify(urlRepository).findByHashCode("fsd");
		assertEquals(false,dataReturned.isStatus());
		assertEquals(ResponseEnum.URLNOTFOUND.getErrorNumber(),dataReturned.getErrorDesc().getErrorCode());
		assertEquals(ResponseEnum.URLNOTFOUND.getErrorMessage(),dataReturned.getErrorDesc().getErrorDesc());
	}
	
	@Test
	public void urlExpiredTest(){
		//Invocation of method
		ResponseWrapper<UrlDTO> dataReturned = this.urlService.getUrlByHashCode(expiredUrl.getHashCode());
		//Verifications for the invoked method
		verify(urlRepository).findByHashCode(expiredUrl.getHashCode());
		assertEquals(false,dataReturned.isStatus());
		assertEquals(ResponseEnum.EXPIREDURL.getErrorNumber(),dataReturned.getErrorDesc().getErrorCode());
		assertEquals(ResponseEnum.EXPIREDURL.getErrorMessage(),dataReturned.getErrorDesc().getErrorDesc());
	}
	
	

}
