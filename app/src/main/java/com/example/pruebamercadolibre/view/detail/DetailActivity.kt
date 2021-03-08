package com.example.pruebamercadolibre.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.application.MyApplication
import com.example.pruebamercadolibre.db.AppDatabase
import com.example.pruebamercadolibre.util.ViewModelFactory
import com.example.pruebamercadolibre.viewmodel.DetailActivityViewModel
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    var appDatabase: AppDatabase? = null
        @Inject set
    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var detailActivityViewModel: DetailActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        (applicationContext as MyApplication).getComponent()?.inject(this)

        detailActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailActivityViewModel::class.java)

        detailActivityViewModel?.getSearchBySite("")
    }
}