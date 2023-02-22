package com.example.rview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ThirdActivity extends Activity {

    TextView title;
    EditText url;
    Button confirm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        title = findViewById(R.id.third_tv_title);
        url = findViewById(R.id.third_et_url);
        confirm = findViewById(R.id.third_bt_confirm);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String content = intent.getExtras().get("content").toString();
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
        url.setText(content);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String content = intent.getExtras().get("content").toString();
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
        url.setText(content);
    }
}
