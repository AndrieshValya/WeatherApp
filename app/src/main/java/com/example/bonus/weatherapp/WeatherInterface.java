package com.example.bonus.weatherapp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {
    @GET("/data/2.5/forecast")
    Call<WeatherOdessa> getWeather(@Query("id") String cityLat, @Query("appid") String appId);
}
