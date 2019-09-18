package com.convertor.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.convertor.conf.port.adapter.aspect.Loggable;
import com.convertor.domain.model.CountryCode;
import com.convertor.domain.model.Vat;
import com.convertor.port.adapter.rest.client.request.VatService;
import com.google.common.base.Optional;


/**
 * @author neelam
 *
 */
@Service
@Loggable
public class ValidateVatService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateVatService.class);
	
	@Autowired
	private VatService vatService;
	
    @Loggable
	public String validate(Double vatNumber) throws Exception {
    	Vat vat = Vat.builder().vatCode(vatNumber).build();
    	ResponseEntity<CountryCode> vatDetails = vatService.validateVatNumber(vat);
    	LOGGER.info("VatNumber: "+vatNumber + "CountryCode: "+vatDetails.getBody().getCountryCode());
    	return vatDetails.getBody().getCountryCode();
	}
}
