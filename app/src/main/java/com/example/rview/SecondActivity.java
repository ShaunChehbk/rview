package com.example.rview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends Activity {
    public static String TAG = "SecondActivity";
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.second_activity);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(SecondActivity.this, "onStart() called", Toast.LENGTH_SHORT).show();
        adapter = new Adapter(this, getList(getIntent().getExtras()));
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("content", adapter.getModelAt(position).toString());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Long press", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //避免返回之前的Activity页面
        this.finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");

    }

    @Override
    public void finish() {
        super.finish();
        Log.i(TAG, "finish()");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(SecondActivity.this, "onNewIntent() called", Toast.LENGTH_SHORT).show();

        adapter.setModels(getList(intent.getExtras()));

    }

    private ArrayList<Model> getList(Bundle bundle) {
        ArrayList<Model> models = new ArrayList<>();
        if (bundle != null) {
            for (String key : bundle.keySet()) {

                Log.i("SecondAct", key + " -> " + bundle.get(key) + ";");
                if (bundle.get(key) != null) {
                    models.add(new Model(bundle.get(key).toString()));
                }
            }
        }

        return models;
    }
}
