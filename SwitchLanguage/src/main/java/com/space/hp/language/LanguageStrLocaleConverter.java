package com.space.hp.language;

import java.util.Locale;

/**
 * @author: HePing
 * @date: 2019/12/27
 * @description: String和Locale对象转换器
 */
public interface LanguageStrLocaleConverter {

    /**
    * String转换Locale；  
    * setLanguage（）
    */
    Locale string2Locale(String language);

    /**
    * Locale对象转换成String
    * getCurrentLanguage（）
    */
    String locale2String(Locale locale);
}
