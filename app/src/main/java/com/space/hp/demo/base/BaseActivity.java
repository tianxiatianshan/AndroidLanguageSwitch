package com.space.hp.demo.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.space.hp.language.LanguageManager;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public abstract class BaseActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.INSTANCE.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageManager.INSTANCE.getLanguageContext(newBase));
    }
}
