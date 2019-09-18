package com.convertor.port.adapter.rest.endpoint;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.convertor.application.service.CurrencyService;
import com.convertor.application.service.ValidateVatService;
import com.convertor.domain.model.Rates;
import com.convertor.domain.model.Currency;
import com.convertor.port.adapter.rest.endpoint.ConvertorResource;

/**
 * @author neelam
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConvertorResourceTest {
	
	@Autowired
	@InjectMocks
	private ConvertorResource rateResource;
	
	@MockBean(name = "currencyService")
	private CurrencyService currencyService;
	@MockBean(name = "validateVatService")
	private ValidateVatService validateVatService;
	
	private static final double vatNumber = 27552120;
	private static final Float amount = 50.00f;
	private static final String sourceCurrency = "INR";
	private static final String targetCurrency = "EUR";
	public static final String OK_RESPONSE = "OK";
	
	ResponseEntity<String> mockResponse;
	LocalTime now;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockResponse = ResponseEntity.ok().body("test");
		now = LocalTime.now();
	}
	

	@Test
	public void testGetTime() {
		Mockito.when(currencyService.process()).thenReturn(now);
		ResponseEntity<String> response = rateResource.getTime();
		assertThat((response.getStatusCode().value())).isEqualTo(HttpStatus.OK_200);
	}

	@Test
	public void testValidateVat() throws Exception {
		Mockito.when(validateVatService.validate(Matchers.anyDouble())).thenReturn("FR");
		ResponseEntity<String> response = rateResource.validateVat(vatNumber);
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK_200);
	}

	@Test
	public void testConvertCurrencyDouble() throws Exception {
		Mockito.when(currencyService.convert(Matchers.anyFloat(), Matchers.anyString(), Matchers.anyString())).thenReturn(4000.00f);
		ResponseEntity<Float> response = rateResource.convertCurrency(amount, sourceCurrency, targetCurrency);
		assertThat(Integer.valueOf(response.getStatusCode().value())).isEqualTo(HttpStatus.OK_200);
		assertThat(response.getBody().doubleValue()).isEqualTo(4000.00);
	}

}
