package com.example.weatherapp.app

import android.content.Context
import androidx.multidex.MultiDex
import com.example.weatherapp.di.component.AppComponent
import com.example.weatherapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: AppComponent =
            DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }

}