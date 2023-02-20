package com.example.rview;

import android.app.Activity;
import android.os.Bundle;

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

        adapter = new Adapter(this, getList(getIntent().getExtras()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private ArrayList<Model> getList(Bundle bundle) {
        ArrayList<Model> models = new ArrayList<>();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                models.add(new Model(bundle.get(key).toString()));
            }
        }



        return models;
    }
}
