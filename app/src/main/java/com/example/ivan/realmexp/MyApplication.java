package com.example.ivan.realmexp;

import android.app.Application;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;

/**
 * Created by ivan on 8/6/17.
 */

public class MyApplication extends Application {

    public static AtomicInteger REALM_ID;

    {
        REALM_ID = new AtomicInteger();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
