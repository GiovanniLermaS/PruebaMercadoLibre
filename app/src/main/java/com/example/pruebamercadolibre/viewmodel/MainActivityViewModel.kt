package com.example.pruebamercadolibre.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebamercadolibre.db.model.Site
import com.example.pruebamercadolibre.repository.MainActivityRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val mainActivityRepository: MainActivityRepository) :
    ViewModel() {

    private val successMain: MutableLiveData<ArrayList<Site>> = MutableLiveData()
    private val errorMain = MutableLiveData<String>()

    fun getSites() {
        mainActivityRepository.getSites().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<ArrayList<Site>>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    errorMain.value = e.message
                }

                override fun onSuccess(sites: Response<ArrayList<Site>>) {
                    try {
                        successMain.value = sites.body()
                    } catch (e: Exception) {
                        errorMain.value =
                            "El servicio para los detalles falló, vuelve a atrás e intenta"
                    }
                }
            })
    }
}
