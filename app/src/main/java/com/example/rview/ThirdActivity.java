package com.example.rview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThirdActivity extends Activity {

    TextView title;
    EditText url;
    Button confirm, expand, save;
    private String filename = "Url.text";
    private String filepath = "MyFileStorage";
    File myExternalFile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        title = findViewById(R.id.third_tv_title);
        url = findViewById(R.id.third_et_url);
        confirm = findViewById(R.id.third_bt_confirm);
        expand = findViewById(R.id.third_bt_expand);
        save = findViewById(R.id.third_bt_save);

        Intent intent = getIntent();
        String content = intent.getExtras().get("content").toString();
        url.setText(content);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.fetchAndUpdate(url.getText().toString(), "title", ThirdActivity.this, title);
            }
        });
        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.expandUrlAndUpdate(url.getText().toString(), ThirdActivity.this, url);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile, true);
                    fos.write((title.getText().toString() + " -|- " + url.getText().toString() + "\n\n").getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(), "Save succeed", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        if (!ActivityUtils.isExternalStorageAvailable() || ActivityUtils.isExternalStorageReadOnly()) {
            save.setEnabled(false);
        }
        else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String content = intent.getExtras().get("content").toString();
        url.setText(content);
    }
}
