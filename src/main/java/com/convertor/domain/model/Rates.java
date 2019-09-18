package com.convertor.domain.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

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
public class Rates{
	/**
	 * 
	 */
	///private static final long serialVersionUID = 6083017686276275265L;
	@Nullable
	@JsonProperty("INR")
    private Float iNR;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}