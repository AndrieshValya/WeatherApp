package com.example.bonus.weatherapp;

import android.app.Application;
import io.realm.Realm;


public class CreateRealm extends Application{
        @Override
        public void onCreate() {
            super.onCreate();
            Realm.init(this);
        }
}


