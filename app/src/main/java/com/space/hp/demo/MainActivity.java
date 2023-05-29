package com.space.hp.demo;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import com.space.hp.demo.base.BaseActivity;
import com.space.hp.language.LanguageManager;
import com.space.hp.language.annotation.AutoLanguage;

/**
 * @author HePing
 * @date 2019/12/26
 */
public class MainActivity extends BaseActivity {

    @AutoLanguage("enter_account")
    EditText userEd;
    @AutoLanguage("enter_password")
    EditText passportEd;
    private int i = 0;
    @AutoLanguage("login")
    Button loginBt;
    @AutoLanguage("switch_language")
    Button floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEd = findViewById(R.id.username);
        passportEd = findViewById(R.id.password);
        loginBt = findViewById(R.id.login);
        floatingActionButton = findViewById(R.id.switch_language);

        final String[] langList = new String[]{"en", "zh", "ko", "ja"};
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageManager.INSTANCE.setLanguage(MainActivity.this, langList[i++%4]);
            }
        });
    }
}
