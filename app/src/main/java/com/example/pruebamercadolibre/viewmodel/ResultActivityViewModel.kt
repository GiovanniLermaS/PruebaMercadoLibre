package com.example.pruebamercadolibre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebamercadolibre.db.model.SearchBySite
import com.example.pruebamercadolibre.repository.ResultActivityRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class ResultActivityViewModel @Inject constructor(private val resultActivityRepository: ResultActivityRepository) :
    ViewModel() {

    private val successResult: MutableLiveData<SearchBySite> = MutableLiveData()
    private val errorResult = MutableLiveData<String>()

    fun getSuccessResult(): LiveData<SearchBySite> {
        return successResult
    }

    fun getErrorResult(): LiveData<String?> {
        return errorResult
    }

    fun getSearchBySite(siteId: String, wordSearch: String) {
        resultActivityRepository.getSearchBySite(siteId, wordSearch).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<SearchBySite>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    errorResult.value = e.message
                }

                override fun onSuccess(searchBySite: Response<SearchBySite>) {
                    try {
                        successResult.value = searchBySite.body()
                    } catch (e: Exception) {
                        errorResult.value =
                            "El servicio falló, vuelve a intentar"
                    }
                }
            })
    }
}
