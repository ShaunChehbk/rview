package com.example.rview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends Activity {

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
        recyclerView.setAdapter(adapter);

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
