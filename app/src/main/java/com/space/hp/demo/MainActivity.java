package com.space.hp.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import com.space.hp.demo.base.BaseActivity;
import com.space.hp.language.LanguageManager;

/**
 * @author HePing
 * @date 2019/12/26
 */
public class MainActivity extends BaseActivity {

    private TextView mTextView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("测试")
                        .setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LanguageManager.INSTANCE.setLanguage(MainActivity.this, editText.getText().toString().trim());
                            }
                        }).show();
            }
        });
    }

    @Override
    public void onLanguageChanged(Context context) {
        mTextView.setText(context.getResources().getString(R.string.hello));
    }
}
