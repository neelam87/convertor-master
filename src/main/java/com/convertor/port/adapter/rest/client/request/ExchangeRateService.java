/**
 * 
 */
package com.convertor.port.adapter.rest.client.request;

import org.springframework.http.ResponseEntity;

import com.convertor.domain.model.Currency;

/**
 * @author neelam
 *
 */
public interface ExchangeRateService {
	ResponseEntity<Currency> getRate(String sourceCurrency, String targetCurrency) throws Exception;

}
