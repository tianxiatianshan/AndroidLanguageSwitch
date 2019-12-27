package com.space.hp.language.Internal;

import android.content.Context;
import com.space.hp.language.LanguageObserver;
import com.space.hp.language.api.ILanguageHelper;
import com.space.hp.language.sp.SPManager;
import com.space.hp.language.util.LanguageUtil;
import com.space.hp.language.util.LogUtil;

import java.util.Locale;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public class LanguageHelperImp implements ILanguageHelper {

    public static final String TAG = "LanguageHelperImp--";

    @Override
    public void registerObserver(LanguageObserver observer) {
        LanguageObserverManager.getInstance().addObserver(observer);
    }

    @Override
    public void unRegisterObserver(LanguageObserver observer) {
        LanguageObserverManager.getInstance().removeObserver(observer);
    }

    @Override
    public void setLanguage(Context context, String language) {
        LogUtil.d(TAG, "setLanguage:  " + language);
        SPManager.setLanguage(context, language);
        SPManager.setIsSetLanguage(context, true);
        Context configContext = LanguageUtil.wrapperConfigContext(context, LanguageUtil.getLocaleFromLanguageStr(language));
        LanguageObserverManager.getInstance().noticeLanguageObserver(configContext);
    }

    @Override
    public void setAutoLanguage(Context context) {
        SPManager.setIsSetLanguage(context, false);
    }

    @Override
    public Context getLanguageContext(Context context) {
        Locale locale;
        if (SPManager.getIsSetLanguage(context)) {
            locale = LanguageUtil.getLocaleFromLanguageStr(SPManager.getLanguage(context));
        } else {
            locale = LanguageUtil.getSystemLocale();
        }

        return LanguageUtil.wrapperConfigContext(context, locale);
    }
}
