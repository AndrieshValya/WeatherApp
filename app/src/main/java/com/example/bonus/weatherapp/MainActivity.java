package com.example.bonus.weatherapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bonus.weatherapp.Data;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmCollection;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    public WeatherInterface Interface;
    public static WeakReference<MainActivity> activity;
    private Retrofit retrofit;
    public WeatherOdessa data;
    public java.util.List<Data> dataData;
    public double id;
    private LocationManager locationManager;
    private Realm realm;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    //tv = findViewById(R.id.textView);
        final RealmResults<WeatherOdessa> realmResult = realm.where(WeatherOdessa.class).findAll();
        realmResult.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<WeatherOdessa>>() {
            @Override
            public void onChange(RealmResults<WeatherOdessa> weather, OrderedCollectionChangeSet changeSet) {
                RealmList<Data> dataList = realmResult.last().getData();
                for (int i = 0; i < realmResult.last().getCnt() - 1; i++) {
                    dataList.get(i).getMain().getTemp(),
                            dataList.get(i).getMain().getPressure(),
                            dataList.get(i).getMain().getHumidity(),
                            dataList.get(i).getWeather().get(0).getDescription(),
                            dataList.get(i).getWind().getSpeed(),
                            dataList.get(i).getWeather().get(0).getIcon());

                }


            }
        });
    }

    public void onClick(View view) {
        startService(new Intent(this, Service.class));
    }
}
