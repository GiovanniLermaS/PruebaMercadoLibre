package com.example.pruebamercadolibre.di.component

import android.app.Application
import android.content.Context
import com.example.pruebamercadolibre.application.MyApplication
import com.example.pruebamercadolibre.di.ApplicationContext
import com.example.pruebamercadolibre.di.DatabaseInfo
import com.example.pruebamercadolibre.di.module.ApplicationModule
import com.example.pruebamercadolibre.di.module.DatabaseModule
import com.example.pruebamercadolibre.di.module.RetrofitModule
import com.example.pruebamercadolibre.view.detail.DetailActivity
import com.example.pruebamercadolibre.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DatabaseModule::class, RetrofitModule::class])
interface ApplicationComponent {
    fun inject(myApplication: MyApplication?)
    fun inject(mainActivity: MainActivity?)
    fun inject(detailActivity: DetailActivity?)

    @get:ApplicationContext
    val context: Context?
    val application: Application?

    @get:DatabaseInfo
    val databaseName: String?
}