/**
 * 
 */
package com.convertor.port.adapter.rest.client.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.convertor.domain.model.Rates;
import com.convertor.domain.model.Currency;
import com.convertor.port.adapter.rest.client.request.ExchangeRateServiceImpl;

/**
 * @author neelam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExchangeRateServiceImplTest {
	
	@MockBean(name = "restTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	@InjectMocks
	private ExchangeRateServiceImpl exchangeRateServiceImpl;
	ResponseEntity<Currency> response;
	

	private static final String sourceCurrency = "INR";
	private static final String targetCurrency = "EUR";
	
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		response = ResponseEntity.ok().body(Currency.builder().rates(Rates.builder().iNR(80.00f).build()).build());
	}

	/**
	 * Test method for {@link com.convertor.port.adapter.rest.client.request.ExchangeRateServiceImpl#getRate(java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetRate() throws Exception {
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Matchers.any(HttpMethod.class),
				Mockito.<HttpEntity<?>>any(), Mockito.<Class<Currency>>any())).thenReturn(response);
		ResponseEntity<Currency> rates = exchangeRateServiceImpl.getRate(sourceCurrency, targetCurrency);
		assertThat((rates.getStatusCode().value())).isEqualTo(HttpStatus.OK_200);
	}
	
	
	@Test(expected =Exception.class)
	public void testGetRateWhenThrowsExceptions() throws Exception {
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Matchers.any(HttpMethod.class),
				Mockito.<HttpEntity<?>>any(), Mockito.<Class<Currency>>any())).thenThrow(HttpClientErrorException.class);
		exchangeRateServiceImpl.getRate(sourceCurrency, targetCurrency);
	}

}
