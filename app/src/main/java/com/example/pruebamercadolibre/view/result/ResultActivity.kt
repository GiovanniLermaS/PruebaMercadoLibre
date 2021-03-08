package com.example.pruebamercadolibre.view.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.application.MyApplication
import com.example.pruebamercadolibre.db.AppDatabase
import com.example.pruebamercadolibre.util.ID_SITE
import com.example.pruebamercadolibre.util.ViewModelFactory
import com.example.pruebamercadolibre.util.WORD_SEARCH
import com.example.pruebamercadolibre.view.result.adapter.ResultAdapter
import com.example.pruebamercadolibre.viewmodel.ResultActivityViewModel
import kotlinx.android.synthetic.main.activity_result.*
import javax.inject.Inject

class ResultActivity : AppCompatActivity() {

    var appDatabase: AppDatabase? = null
        @Inject set
    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var resultActivityViewModel: ResultActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        (applicationContext as MyApplication).getComponent()?.inject(this)

        resultActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ResultActivityViewModel::class.java)
        resultActivityViewModel?.getSearchBySite(
            intent.getStringExtra(ID_SITE)!!,
            intent.getStringExtra(WORD_SEARCH)!!
        )
        resultActivityViewModel?.getSuccessResult()?.observe(this) { searchBySite ->
            rvSearchBySite.adapter = ResultAdapter(this, searchBySite.results)
        }
    }
}