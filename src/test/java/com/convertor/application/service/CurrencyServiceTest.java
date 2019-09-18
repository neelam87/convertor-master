package com.convertor.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.convertor.application.service.CurrencyService;
import com.convertor.domain.model.Rates;
import com.convertor.domain.model.Currency;
import com.convertor.port.adapter.rest.client.request.ExchangeRateService;

/**
 * @author neelam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CurrencyServiceTest {
	
	@MockBean(name = "exchangeRateService")
	private ExchangeRateService exchangeRateService;
	
	@Autowired
	@InjectMocks
	private CurrencyService currencyService;
	
	ResponseEntity<Currency> response;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		response = ResponseEntity.ok().body(Currency.builder().rates(Rates.builder().iNR(80.00f).build()).build());
	}

	/**
	 * Test method for {@link com.convertor.application.service.CurrencyService#process()}.
	 */
	@Test
	public void testProcess() {
		LocalTime localTime = currencyService.process();
		assertThat(localTime).isNotNull();
	}

	/**
	 * Test method for {@link com.convertor.application.service.CurrencyService#convert(java.lang.Double, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testConvert() throws Exception {
		Mockito.when(exchangeRateService.getRate(Matchers.anyString(), Matchers.anyString())).thenReturn(response);
		Float amt = currencyService.convert(20.00f, "INR", "EUR");
		assertThat(amt).isEqualTo(1600.00);
		
	}

}
