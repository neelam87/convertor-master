package com.convertor.port.adapter.rest.endpoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.convertor.application.service.CurrencyService;
import com.convertor.application.service.ValidateVatService;
import com.convertor.conf.port.adapter.aspect.Loggable;
import com.convertor.domain.model.CountryCode;
import com.convertor.port.adapter.rest.client.request.ExchangeRateServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author neelam
 *
 */

@RestController
@RequestMapping("/convertor")
@Api(tags = "Conversion service", produces = "Get the current time, currency conversion, country code for a vat number")
public class ConvertorResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConvertorResource.class);
    
    @Autowired
    CurrencyService currencyService;
    
    @Autowired
    ValidateVatService validateVatService;

      
    @ApiOperation(value = "Get current time ",
            notes = "Get current time")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Current time sucess")})
    @GetMapping(value = "/currenttime", produces = "application/text")
    @Loggable
    @ResponseBody
    public ResponseEntity<String> getTime() {
    	LOGGER.info("get current time");
        return ResponseEntity.ok()
                .body(currencyService.process().toString());
    }
    
    @ApiOperation(value = "Convert the currency",
            notes = "Convert the currency")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Convert the currency")})
    @GetMapping(value = "/convert", produces = "application/json")
    @Loggable
    @ResponseBody
    public ResponseEntity<Float> convertCurrency(@ApiParam("Amount") @RequestParam("amount") Float amount,
            @ApiParam("Source Currency") @RequestParam("sourceCurrency") String sourceCurrency,
            @ApiParam("Target Currency") @RequestParam("targetCurrency") String targetCurrency) throws Exception {
    	LOGGER.info("convert amount from sourceCurrency to targetCurrency");
        return ResponseEntity.ok()
                .body(currencyService.convert(amount, sourceCurrency, targetCurrency));
    }
    
    @ApiOperation(value = "Validate VAT",
            notes = "Validate VAT number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Country code for VAT number")})
    @PostMapping(value = "/validate", produces = "application/json")
    @Loggable
    @ResponseBody
    public ResponseEntity<String> validateVat(@ApiParam("VAT Number") @RequestParam("vatNumber") Double vatNumber) throws Exception {
    	LOGGER.info("validate the vat number");
        return ResponseEntity.ok()
                .body(validateVatService.validate(vatNumber));
    }
}
