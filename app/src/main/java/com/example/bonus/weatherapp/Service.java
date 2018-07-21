package com.example.bonus.weatherapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;


import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Service extends IntentService {
    public WeatherInterface retroInterface;
    private Realm realm;
    private Retrofit retrofit;

    public Service() {
        super("myname");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create()).build();
        retroInterface = retrofit.create(WeatherInterface.class);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        realm = Realm.getDefaultInstance();
        try {
            Log.d("RUN", "Thread started.");
            Call<WeatherOdessa> responseCall = retroInterface.getWeather(String.valueOf(5527554), "2e8071162c252667850af8964461d855");
            Response<WeatherOdessa> res = responseCall.execute();
            realm.beginTransaction();
            realm.insert(res.body());
            realm.commitTransaction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
