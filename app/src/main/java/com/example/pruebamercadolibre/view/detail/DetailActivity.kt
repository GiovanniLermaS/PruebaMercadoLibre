package com.example.pruebamercadolibre.view.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.application.MyApplication
import com.example.pruebamercadolibre.db.AppDatabase
import com.example.pruebamercadolibre.util.IDS_INTENT
import com.example.pruebamercadolibre.util.ViewModelFactory
import com.example.pruebamercadolibre.util.formatter
import com.example.pruebamercadolibre.util.showProgress
import com.example.pruebamercadolibre.view.detail.adapter.ViewPagerAdapter
import com.example.pruebamercadolibre.viewmodel.DetailActivityViewModel
import kotlinx.android.synthetic.main.activity_detail.*
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
        showProgress(this, isAlertInit = true)
        (applicationContext as MyApplication).getComponent()?.inject(this)

        detailActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailActivityViewModel::class.java)
        consumeDetailByIdSearch()
    }

    private fun consumeDetailByIdSearch() {
        detailActivityViewModel?.getDetailByIdSearch(intent.getStringExtra(IDS_INTENT)!!)
        detailActivityViewModel?.getSuccessDetail()?.observe(this) { detail ->
            val body = detail[0].body
            tvDetailTitle.text = body.title
            tvAttributeNameOne.text = body.attributes[0].name
            tvAttributeValueOne.text = body.attributes[0].value_name
            tvAttributeNameTwo.text = body.attributes[1].name
            tvAttributeValueTwo.text = body.attributes[1].value_name
            tvAttributeNameThree.text = body.attributes[2].name
            tvAttributeValueThree.text = body.attributes[2].value_name
            tvAttributeNameFour.text = body.attributes[3].name
            tvAttributeValueFour.text = body.attributes[3].value_name
            body.pictures
            tvPriceDetail.text = formatter.format(body.price)
            vpImages.adapter = ViewPagerAdapter(this, body.pictures)
            showProgress(this, isAlertInit = false)
        }
        detailActivityViewModel?.getErrorDetail()?.observe(this) { message ->
            Log.e("Error consume service", message!!)
            showProgress(this, isAlertInit = false)
        }
    }
}