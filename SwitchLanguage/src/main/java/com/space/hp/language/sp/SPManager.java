package com.space.hp.language.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public class SPManager {

    private static final String PREFER_FILE_NAME = "prefer_file_name";

    private static final String PREFER_PARAM_IS_SET_LANGUAGE = "prefer_param_is_set_language";
    private static final String PREFER_PARAM_LANGUAGE = "prefer_param_language";

    public static void setIsSetLanguage(Context context, boolean isSetLanguage) {
        SharedPreferences preferences = context.getSharedPreferences(PREFER_FILE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(PREFER_PARAM_IS_SET_LANGUAGE, isSetLanguage).apply();
    }

    public static boolean getIsSetLanguage(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFER_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(PREFER_PARAM_IS_SET_LANGUAGE, false);
    }

    public static void setLanguage(Context context, String language) {
        SharedPreferences preferences = context.getSharedPreferences(PREFER_FILE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(PREFER_PARAM_LANGUAGE, language).apply();
    }

    public static String getLanguage(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFER_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(PREFER_PARAM_LANGUAGE, "");
    }

}
