package com.example.rview;

import android.app.Activity;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

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

    public static void expandUrlAndUpdate(String shortenedUrl, Activity activity, EditText editText) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(shortenedUrl);
                    // open connection
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);

                    // stop following browser redirect
                    httpURLConnection.setInstanceFollowRedirects(false);

                    // extract location header containing the actual destination URL
                    String expandedURL = httpURLConnection.getHeaderField("Location");
                    httpURLConnection.disconnect();

                    if (!expandedURL.equals("")) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                editText.setText(expandedURL);
                            }
                        });
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

}
