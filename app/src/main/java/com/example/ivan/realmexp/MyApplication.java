package com.example.ivan.realmexp;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by ivan on 8/6/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
