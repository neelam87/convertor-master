package com.convertor.conf.port.adapter.aspect;

import java.lang.annotation.*;


/**
 * @author neelam
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Loggable {
    boolean duration() default false;

    boolean size() default false;
}