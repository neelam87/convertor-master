/**
 * 
 */
package com.convertor.port.adapter.rest.client.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.convertor.domain.model.CountryCode;
import com.convertor.domain.model.Vat;
import com.convertor.exception.ClientException;
import com.convertor.util.ConvertorConstants;

/**
 * @author neelam
 *
 */
@Service
public class VatServiceImpl implements VatService {
	private static final Logger LOGGER = LoggerFactory.getLogger(VatServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${vat.url}")
	private String vatUrl = "https://api.cloudmersive.com/validate/vat/lookup";

	public VatServiceImpl(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public ResponseEntity<CountryCode> validateVatNumber(Vat vat) throws Exception{
		try {
			ResponseEntity<CountryCode> response = restTemplate.postForEntity(vatUrl, vat, CountryCode.class);
			LOGGER.info("countryCode response" + response.getStatusCodeValue());
			return response;
		} catch (HttpClientErrorException ex) {
			LOGGER.error(
					ex + System.lineSeparator() + ex.getResponseBodyAsString() + ": \t" + ex.getLocalizedMessage());
			throw new ClientException(ConvertorConstants.ERROR_VAT, ex);
		}
	}

}
