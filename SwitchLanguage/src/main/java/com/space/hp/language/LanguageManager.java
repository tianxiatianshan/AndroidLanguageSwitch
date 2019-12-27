package com.space.hp.language;

import android.content.Context;
import androidx.annotation.MainThread;
import com.space.hp.language.Internal.LanguageHelperImp;
import com.space.hp.language.Internal.LifecycleImp;
import com.space.hp.language.api.ILanguageHelper;
import com.space.hp.language.api.ILanguageManager;
import com.space.hp.language.api.ILifecycle;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public enum LanguageManager implements ILanguageManager {
    /**
     * 单例
     */
    INSTANCE;

    private ILifecycle mLifecycle;
    private ILanguageHelper mLanguageHelper;

    LanguageManager() {
        mLanguageHelper = new LanguageHelperImp();
        mLifecycle = new LifecycleImp();
    }

    @Override
    @MainThread
    public void registerObserver(LanguageObserver observer) {
        mLanguageHelper.registerObserver(observer);
    }

    @Override
    public void unRegisterObserver(LanguageObserver observer) {
        mLanguageHelper.unRegisterObserver(observer);
    }

    @Override
    @MainThread
    public void setLanguage(Context context, String language) {
        mLanguageHelper.setLanguage(context, language);
    }

    @Override
    @MainThread
    public void setAutoLanguage(Context context) {
        mLanguageHelper.setAutoLanguage(context);
    }

    @Override
    @MainThread
    public Context getLanguageContext(Context context) {
        return mLanguageHelper.getLanguageContext(context);
    }

    @Override
    @MainThread
    public void onConfigurationChanged(Context context, LanguageObserver observer) {
        mLifecycle.onConfigurationChanged(context, observer);
    }
}
