package com.example.rview;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ActivityUtils {
//    public static void startActivity()
    public static void fetchAndUpdate(String url, String tag, Activity activity, TextView textView) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(url).get();
                    Element element = document.select(tag).first();
                    Log.i("Utils", element.text());
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(element.text());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
