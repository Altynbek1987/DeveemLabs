package com.example.deveemlabs;

import android.app.Application;

import com.example.deveemlabs.data.service.ServiceApi;

public class App extends Application {
    public static ServiceApi serviceApi;
    @Override
    public void onCreate(){
        super.onCreate();
        serviceApi = new ServiceApi();
    }
}
