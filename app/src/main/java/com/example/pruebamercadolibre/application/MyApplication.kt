package com.example.pruebamercadolibre.application

import android.app.Application
import com.example.pruebamercadolibre.di.component.ApplicationComponent
import com.example.pruebamercadolibre.di.component.DaggerApplicationComponent
import com.example.pruebamercadolibre.di.module.ApplicationModule
import com.example.pruebamercadolibre.di.module.DatabaseModule
import com.example.pruebamercadolibre.di.module.RetrofitModule

class MyApplication : Application() {

    var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .retrofitModule(RetrofitModule(this))
            .databaseModule(DatabaseModule(this))
            .build()
        mApplicationComponent?.inject(this)
    }

    fun getComponent(): ApplicationComponent? {
        return mApplicationComponent
    }
}