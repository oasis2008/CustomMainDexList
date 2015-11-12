package com.udacity.gradle.multidex;

import android.support.multidex.MultiDexApplication;

/**
 * Created by young on 2015/11/13.
 */
public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        new Methods100().method_0();
        new Methods13().method_0();
    }
}
