package com.aswdc_dsconversion;

import android.app.Application;
import android.widget.Toast;

public class AppController extends Application {
    static AppController instance;

    public static AppController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public void showToast(int stringToDisplay) {
        Toast.makeText(getApplicationContext(),
                stringToDisplay, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String stringToDisplay) {
        Toast.makeText(getApplicationContext(),
                stringToDisplay, Toast.LENGTH_SHORT).show();
    }
}

