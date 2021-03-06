package com.example.pruebamercadolibre.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.application.MyApplication
import com.example.pruebamercadolibre.db.AppDatabase
import com.example.pruebamercadolibre.util.ViewModelFactory
import com.example.pruebamercadolibre.viewmodel.MainActivityViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    var appDatabase: AppDatabase? = null
        @Inject set
    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var mainActivityViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApplication).getComponent()?.inject(this)

        mainActivityViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }
}