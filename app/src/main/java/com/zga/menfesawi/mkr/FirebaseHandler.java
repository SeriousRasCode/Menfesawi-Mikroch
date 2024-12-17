package com.zga.menfesawi.mkr;

import android.app.Application;
import android.content.Context;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHandler extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public static Context getContext() {
        return context;
    }
}