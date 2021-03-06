package com.example.pruebamercadolibre.di.module

import android.content.Context
import com.example.pruebamercadolibre.di.ApplicationContext
import com.example.pruebamercadolibre.di.DatabaseInfo
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(@ApplicationContext val context: Context) {
    @DatabaseInfo
    private val dbName = "my_list"

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return dbName
    }
}