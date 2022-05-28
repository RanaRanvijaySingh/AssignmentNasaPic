package com.simple.simpletestapp.di

import android.content.Context
import com.simple.simpletestapp.MyApplication
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun providesApplication(): MyApplication {
        return application
    }

    @Provides
    @Singleton
    fun providesContext(application: MyApplication): Context {
        return application.baseContext
    }

    @Provides
    @Singleton
    fun providesCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}
