package com.space.hp.demo;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import com.space.hp.demo.base.BaseActivity;
import com.space.hp.language.LanguageManager;

/**
 * @author HePing
 * @date 2019/12/26
 */
public class MainActivity extends BaseActivity {
    private EditText userEd;
    private EditText passportEd;
    private int i = 0;
    private Button loginBt;
    private Button floatingActionButton;

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

    @Override
    public void onLanguageChanged(Context context) {

        userEd.setHint(context.getResources().getString(R.string.enter_account));
        passportEd.setHint(context.getResources().getString(R.string.enter_password));
        loginBt.setText(context.getResources().getString(R.string.login));
        floatingActionButton.setText(context.getResources().getString(R.string.switch_language));
    }
}
