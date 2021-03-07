package com.example.pruebamercadolibre.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.application.MyApplication
import com.example.pruebamercadolibre.db.AppDatabase
import com.example.pruebamercadolibre.db.Executor
import com.example.pruebamercadolibre.db.model.Site
import com.example.pruebamercadolibre.util.*
import com.example.pruebamercadolibre.view.result.ResultActivity
import com.example.pruebamercadolibre.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
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
        lifecycleScope.launch {
            if (appDatabase?.siteDao()?.getSites()?.isNullOrEmpty()!!) {
                getCurrency()
                getSites()
            } else {
                showProgress(this@MainActivity, isAlertInit = false, isShow = false)
            }
        }
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
                    Executor.iOThread { appDatabase?.siteDao()?.setSite(site) }
                    showProgress(this, isAlertInit = false, isShow = false)
                    break
                }
            }
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btContinue) {
            if (etWordSearch?.text?.isEmpty()!!) {
                etWordSearch?.requestFocus()
                etWordSearch?.setError(
                    getString(R.string.completeField),
                    ResourcesCompat.getDrawable(
                        resources,
                        android.R.drawable.stat_notify_error,
                        null
                    )
                )
            } else {
                lifecycleScope.launch {
                    val sites = appDatabase?.siteDao()?.getSites() as ArrayList<Site>
                    if (!sites.isNullOrEmpty()) {
                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        intent.putExtra(WORD_SEARCH, etWordSearch.text)
                        intent.putExtra(SITE, sites[0].id)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}