/**
 * 
 */
package com.convertor.domain.model;

import javax.annotation.Nullable;

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
public class Vat {
@Nullable
private Double vatCode;

}
