/**
 * 
 */
package com.convertor.port.adapter.rest.client.request;

import org.springframework.http.ResponseEntity;

import com.convertor.domain.model.CountryCode;
import com.convertor.domain.model.Currency;
import com.convertor.domain.model.Vat;

/**
 * @author neelam
 *
 */
public interface VatService {
	ResponseEntity<CountryCode> validateVatNumber(Vat vat) throws Exception;
}
