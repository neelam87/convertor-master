/**
 * 
 */
package com.convertor.domain.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author neelam
 *
 */
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Currency{
	/**
	 * 
	 */
	//private static final long serialVersionUID = -7717703337630542040L;
	@JsonProperty("rates")
    private Rates rates;
    @JsonProperty("base")
    private String base;
    @JsonProperty("date")
    private String date;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}