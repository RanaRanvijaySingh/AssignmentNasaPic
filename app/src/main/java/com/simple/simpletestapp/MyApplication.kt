package com.simple.simpletestapp

import android.app.Application
import com.simple.simpletestapp.di.AppComponent
import com.simple.simpletestapp.di.AppModule
import com.simple.simpletestapp.di.DaggerAppComponent
import com.simple.simpletestapp.di.NetworkModule
import com.simple.simpletestapp.utils.Constants

class MyApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(Constants.APIS.BASE_URL))
            .build()
    }

    fun getAppComponent(): AppComponent{
        return appComponent
    }
}