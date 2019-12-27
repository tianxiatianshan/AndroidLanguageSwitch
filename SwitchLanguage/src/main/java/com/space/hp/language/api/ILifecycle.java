package com.space.hp.language.api;

import android.content.Context;
import com.space.hp.language.LanguageObserver;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public interface ILifecycle {

    void onConfigurationChanged(Context context, LanguageObserver observer);

}
