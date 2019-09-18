/**
 * 
 */
package com.convertor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author neelam
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientException(String message, HttpClientErrorException ex) {
		super(message, ex);
	}
	
}
