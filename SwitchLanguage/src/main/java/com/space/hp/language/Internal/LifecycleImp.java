package com.space.hp.language.Internal;

import android.content.Context;
import com.space.hp.language.LanguageObserver;
import com.space.hp.language.api.ILifecycle;
import com.space.hp.language.sp.SPManager;
import com.space.hp.language.util.LanguageUtil;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public class LifecycleImp implements ILifecycle {

    @Override
    public void onConfigurationChanged(Context context, LanguageObserver observer) {
        if (!SPManager.getIsSetLanguage(context)) {
            observer.onLanguageChanged(LanguageUtil.wrapperConfigContext(context, LanguageUtil.getSystemLocale()));
        }
    }

}
