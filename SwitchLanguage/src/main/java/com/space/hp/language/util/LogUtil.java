package com.space.hp.language.util;

import android.util.Log;

/**
 * @author: HePing
 * @date: 2019/12/27
 * @description:
 */
public class LogUtil {
    public static final String LOG_PRE_TAG = "-->> ";

    public static void d(String tag, String message) {
        Log.d(LOG_PRE_TAG + tag, message);
    }
}
