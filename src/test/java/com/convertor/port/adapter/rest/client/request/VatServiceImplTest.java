/**
 * 
 */
package com.convertor.port.adapter.rest.client.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.convertor.domain.model.CountryCode;
import com.convertor.domain.model.Rates;
import com.convertor.domain.model.Currency;
import com.convertor.domain.model.Vat;
import com.convertor.port.adapter.rest.client.request.VatServiceImpl;

/**
 * @author neelam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VatServiceImplTest {
	
	@MockBean(name = "restTemplate")
	private RestTemplate restTemplate;
	
	@InjectMocks
	private VatServiceImpl vatServiceImpl;
	ResponseEntity<CountryCode> response;
	
	private static final double vatNumber = 27552120;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		response = ResponseEntity.ok().body(CountryCode.builder().countryCode("FR").build());
	}

	/**
	 * Test method for {@link com.convertor.port.adapter.rest.client.request.VatServiceImpl#validateVatNumber(com.convertor.domain.model.Vat)}.
	 * @throws Exception 
	 */
	@Test
	public void testValidateVatNumber() throws Exception {
		Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Matchers.any(Vat.class),
				 Mockito.<Class<CountryCode>>any())).thenReturn(response);
		ResponseEntity<CountryCode> resp = vatServiceImpl.validateVatNumber(Vat.builder().vatCode(vatNumber).build());
		assertThat((resp.getStatusCode().value())).isEqualTo(HttpStatus.OK_200);
	}
	
	@Test(expected =Exception.class)
	public void testValidateVatNumberWhenThrowsExceptions() throws Exception {
		Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Matchers.any(Vat.class),
				 Mockito.<Class<CountryCode>>any())).thenThrow(HttpClientErrorException.class);
		vatServiceImpl.validateVatNumber(Vat.builder().vatCode(vatNumber).build());
	}

}
