package es.osoco.urlshortener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import es.osoco.urlshortener.api.utils.ResponseWrapper;
import es.osoco.urlshortener.entity.Url;
import es.osoco.urlshortener.model.UrlDTO;
import es.osoco.urlshortener.repository.UrlRepository;
import es.osoco.urlshortener.service.UrlService;
import es.osoco.urlshortener.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceMockTest {

	@Mock
	UrlRepository urlRepository;
	
	@InjectMocks
	UrlService urlService;
	
	Url returnedUrlAA;
	
	@Before
	public void initialisation(){
		returnedUrlAA = new Url();
		returnedUrlAA.setHashCode("aa");
		returnedUrlAA.setUrlLong("http://www.google.es");
		when(urlRepository.findByHashCode(returnedUrlAA.getHashCode())).thenReturn(returnedUrlAA);
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
	public void checkUtils(){
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
	
	

}
