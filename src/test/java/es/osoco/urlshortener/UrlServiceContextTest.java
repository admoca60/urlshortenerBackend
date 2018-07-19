package es.osoco.urlshortener;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import es.osoco.urlshortener.entity.Url;
import es.osoco.urlshortener.repository.UrlRepository;
import es.osoco.urlshortener.service.UrlService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlServiceContextTest {

	@MockBean
	UrlRepository urlRepository;
	
	@Autowired
	UrlService urlService;
	
	Url returnedUrlAA;
	
	@Before
	public void initialisation(){
		System.out.println("Executed before statement");
		returnedUrlAA = new Url();
		returnedUrlAA.setHashCode("aa");
		returnedUrlAA.setUrlLong("http://www.google.es");
		when(urlRepository.findByHashCode("aa")).thenReturn(returnedUrlAA);
	}
	
	@Test
	public void initialTest(){
		this.urlService.getUrlByHashCode("aa");
		verify(urlRepository).findByHashCode("aa");
	}
	
	@Test
	public void contextLoads() {
	}

}
