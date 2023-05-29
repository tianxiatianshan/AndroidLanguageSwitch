package com.space.hp.language.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author HePing
 * @date 2023/5/23
 * @description
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface AutoLanguage {
    String value();
}
