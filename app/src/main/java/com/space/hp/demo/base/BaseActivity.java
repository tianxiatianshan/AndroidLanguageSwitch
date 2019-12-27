package com.space.hp.demo.base;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.space.hp.language.LanguageManager;
import com.space.hp.language.LanguageObserver;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public abstract class BaseActivity extends AppCompatActivity implements LanguageObserver {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.INSTANCE.registerObserver(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageManager.INSTANCE.getLanguageContext(newBase));
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LanguageManager.INSTANCE.onConfigurationChanged(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LanguageManager.INSTANCE.unRegisterObserver(this);
    }
}
