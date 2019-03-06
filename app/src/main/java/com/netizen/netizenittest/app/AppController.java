package com.netizen.netizenittest.app;

import android.app.Application;
import android.content.Context;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static Context sContext;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
}
