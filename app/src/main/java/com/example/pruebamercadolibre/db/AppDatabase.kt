package com.example.pruebamercadolibre.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebamercadolibre.db.dao.SiteDao
import com.example.pruebamercadolibre.db.model.Site

@Database(
    entities = [Site::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun siteDao(): SiteDao
}