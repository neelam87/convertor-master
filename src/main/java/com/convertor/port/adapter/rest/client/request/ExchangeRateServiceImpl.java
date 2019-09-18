/**
 * 
 */
package com.convertor.port.adapter.rest.client.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.convertor.domain.model.Rates;
import com.convertor.domain.model.Currency;
import com.convertor.exception.ClientException;
import com.convertor.util.ConvertorConstants;


/**
 * @author neelam
 *
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${rate.url}")
	private String rateUrl;

	public ExchangeRateServiceImpl(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	
	@Override
	public ResponseEntity<Currency> getRate(String sourceCurrency, String targetCurrency) throws Exception {
	  try {
		ResponseEntity<Currency> response = restTemplate.getForEntity(rateUrl + "?base=" + sourceCurrency+"&symbols="+targetCurrency, Currency.class);
		LOGGER.info("rate response"+response.getStatusCodeValue());
		return response;
		}catch (HttpClientErrorException ex) {
			LOGGER.error(
					ex + System.lineSeparator() + ex.getResponseBodyAsString() + ": \t" + ex.getLocalizedMessage());
			throw new ClientException(ConvertorConstants.ERROR_RATE, ex);
		}
		
		
	}

}
