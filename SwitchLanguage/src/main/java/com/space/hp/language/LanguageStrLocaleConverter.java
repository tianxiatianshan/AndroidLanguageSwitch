package com.space.hp.language;

import java.util.Locale;

/**
 * @author: HePing
 * @date: 2019/12/27
 * @description:
 */
public interface LanguageStrLocaleConverter {

    Locale string2Locale(String language);

    String locale2String(Locale locale);
}
