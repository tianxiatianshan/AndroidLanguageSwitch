package com.space.hp.language.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public class LanguageUtil {

    public static Locale getLocaleFromLanguageStr(String languageStr) {
        if (TextUtils.isEmpty(languageStr)) {
            return Locale.ENGLISH;
        }
        String[] split = languageStr.split("-");
        if (split.length > 1) {
            return new Locale(split[0], split[1]);
        }
        return new Locale(split[0]);
    }

    public static String getStringFormLocale(Locale locale) {
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

    public static Context wrapperConfigContext(Context context, Locale locale) {
        if (locale == null || context == null) {
            return context;
        }
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
            return context.createConfigurationContext(configuration);
        } else {
            configuration.locale = locale;
            resources.updateConfiguration(configuration, metrics);
            return context;
        }
    }

    /**
     * 获取系统Locale
     *
     * @return locale
     */
    public static Locale getSystemLocale() {
        Locale locale;
        Configuration configuration = Resources.getSystem().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = configuration.getLocales().get(0);
        } else {
            locale = configuration.locale;
        }
        return locale;
    }


    public static Context wrapperConfigContext(Context context, String language) {
        return wrapperConfigContext(context, getLocaleFromLanguageStr(language));
    }
}
