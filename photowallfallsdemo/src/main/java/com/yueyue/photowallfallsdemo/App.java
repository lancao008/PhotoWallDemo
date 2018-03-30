package com.yueyue.photowallfallsdemo;

import android.app.Application;
import android.content.Context;

/**
 * author : yueyue on 2018/3/30 11:32
 * desc   :
 */

public class App extends Application{

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext=this;
    }

    public static Context getContext() {
        return sContext;
    }
}
