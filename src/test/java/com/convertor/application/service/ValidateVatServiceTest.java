/**
 * 
 */
package com.convertor.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

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

import com.convertor.application.service.ValidateVatService;
import com.convertor.domain.model.CountryCode;
import com.convertor.domain.model.Rates;
import com.convertor.domain.model.Currency;
import com.convertor.domain.model.Vat;
import com.convertor.port.adapter.rest.client.request.ExchangeRateService;
import com.convertor.port.adapter.rest.client.request.VatService;

/**
 * @author neelam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ValidateVatServiceTest {
	
	@MockBean(name = "vatService")
	private VatService vatService;
	
	@Autowired
	@InjectMocks
	private ValidateVatService validateVatService;
	ResponseEntity<CountryCode> response;
	private static final double vatNumber = 27552120 ;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		response = ResponseEntity.ok().body(CountryCode.builder().countryCode("FR").build());
	}

	/**
	 * Test method for {@link com.convertor.application.service.ValidateVatService#validate(java.lang.Double)}.
	 * @throws Exception 
	 */
	@Test
	public void testValidate() throws Exception {
		Mockito.when(vatService.validateVatNumber(Matchers.any(Vat.class))).thenReturn(response);
		String countryCode = validateVatService.validate(vatNumber);
		assertThat(countryCode).isEqualTo("FR");
	}

}
