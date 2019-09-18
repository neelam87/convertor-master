package com.convertor.application.service;


import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.convertor.conf.port.adapter.aspect.Loggable;
import com.convertor.domain.model.Currency;
import com.convertor.port.adapter.rest.client.request.ExchangeRateService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author neelam
 *
 */
@Slf4j
@Service
@Loggable
public class CurrencyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	
    @Loggable
    public LocalTime process() {
    	LocalTime now = LocalTime.now();
		return now;
    }

    @Loggable
	public Float convert(Float amount, String sourceCurrency, String targetCurrency) throws Exception {
    	ResponseEntity<Currency> rates = exchangeRateService.getRate(sourceCurrency, targetCurrency);
    	Float rate = rates.getBody().getRates().getINR();
    	LOGGER.info("rate value for target currency: "+rate);
		return rate*amount;
	}
}
