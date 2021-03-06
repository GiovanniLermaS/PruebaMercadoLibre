package com.example.pruebamercadolibre.view.main

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.application.MyApplication
import com.example.pruebamercadolibre.db.AppDatabase
import com.example.pruebamercadolibre.util.COP
import com.example.pruebamercadolibre.util.ViewModelFactory
import com.example.pruebamercadolibre.util.showProgress
import com.example.pruebamercadolibre.viewmodel.MainActivityViewModel
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var appDatabase: AppDatabase? = null
        @Inject set
    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var mainActivityViewModel: MainActivityViewModel? = null
    var currency: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showProgress(this, isAlertInit = true, isShow = true)
        (applicationContext as MyApplication).getComponent()?.inject(this)

        mainActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
        getCurrency()
        getSites()
    }

    private fun getCurrency() {
        currency = try {
            val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            Currency.getInstance(Locale("", tm.networkCountryIso)).currencyCode
        } catch (e: Exception) {
            //Use GPS for example
            COP
        }
    }

    private fun getSites() {
        mainActivityViewModel?.getSites()
        mainActivityViewModel?.getSuccessMain()?.observe(this) { listSites ->
            for (site in listSites) {
                if (currency?.equals(site.defaultCurrencyId)!!) {
                    Log.e("Site", site.toString())
                    showProgress(this, isAlertInit = false, isShow = false)
                    break
                }
            }
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btContinue) {

        }
    }
}