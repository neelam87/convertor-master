
package com.convertor.domain.model;

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
public class CountryCode {

    private String countryCode;
    private String vatNumber;
    private Boolean isValid;
    private String businessName;
    private String businessAddress;
}
