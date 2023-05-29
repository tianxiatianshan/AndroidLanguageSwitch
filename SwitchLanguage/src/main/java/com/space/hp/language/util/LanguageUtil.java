package com.space.hp.language.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public class LanguageUtil {


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

    public static String getString(Context context, String name) {
        Resources resources = context.getResources();
        int strId = resources.getIdentifier(name, "string", context.getPackageName());
        if (strId <= 0) {
            LogUtil.e("LanguageUtil",  String.format("don't find %s in string xml", name));
            return "";
        } else {
            return context.getResources().getString(strId);
        }
    }
}
