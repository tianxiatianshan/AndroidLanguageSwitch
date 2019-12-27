package com.space.hp.language.api;

import android.content.Context;
import com.space.hp.language.LanguageConverter;
import com.space.hp.language.LanguageObserver;

/**
 * @author HePing
 * @date 2019/12/26
 * @description
 */
public interface ILanguageHelper {

    void registerObserver(LanguageObserver observer);

    void unRegisterObserver(LanguageObserver observer);

    void setLanguage(Context context, String language);

    String getCurrentLanguage(Context context);

    void setAutoLanguage(Context context);

    Context getLanguageContext(Context context);

    void setLanguageConverter(LanguageConverter languageConverter);
}
