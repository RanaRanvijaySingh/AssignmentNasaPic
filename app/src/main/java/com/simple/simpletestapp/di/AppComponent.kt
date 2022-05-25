package com.simple.simpletestapp.di

import com.simple.simpletestapp.presentation.viewmodels.ViewModelModule
import com.simple.simpletestapp.presentation.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        CommonModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
}