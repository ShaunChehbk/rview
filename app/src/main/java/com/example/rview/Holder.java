package com.example.rview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {

    TextView textView;

    public Holder(@NonNull View itemView) {
        super(itemView);
        this.textView = itemView.findViewById(R.id.tv_item);
    }
}
