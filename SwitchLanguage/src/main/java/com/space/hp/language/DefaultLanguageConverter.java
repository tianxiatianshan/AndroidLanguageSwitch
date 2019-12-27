package com.space.hp.language;

import android.text.TextUtils;

import java.util.Locale;

/**
 * @author: HePing
 * @date: 2019/12/27
 * @description:
 */
public class DefaultLanguageConverter implements LanguageConverter {
    @Override
    public Locale string2Locale(String language) {
        if (TextUtils.isEmpty(language)) {
            return Locale.ENGLISH;
        }
        String[] split = language.split("-");
        if (split.length > 1) {
            return new Locale(split[0], split[1]);
        }
        return new Locale(split[0]);
    }

    @Override
    public String locale2String(Locale locale) {
        if (locale == null) {
            locale = Locale.ENGLISH;
        }
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if (TextUtils.isEmpty(country)) {
            return language;
        }
        return language + "-" + country;
    }
}
