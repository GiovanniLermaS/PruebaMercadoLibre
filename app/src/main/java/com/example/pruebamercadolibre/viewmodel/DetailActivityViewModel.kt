package com.example.pruebamercadolibre.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebamercadolibre.db.model.SearchBySite
import com.example.pruebamercadolibre.repository.DetailActivityRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(private val detailActivityRepository: DetailActivityRepository) :
    ViewModel() {

    private val successDetail: MutableLiveData<SearchBySite> = MutableLiveData()
    private val errorDetail = MutableLiveData<String>()

    fun getSearchBySite(idSearch: String) {
        detailActivityRepository.getDetailByIdSearch(idSearch).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<SearchBySite>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    errorDetail.value = e.message
                }

                override fun onSuccess(searchBySite: Response<SearchBySite>) {
                    try {
                        successDetail.value = searchBySite.body()
                    } catch (e: Exception) {
                        errorDetail.value =
                            "El servicio falló, vuelve a intentar"
                    }
                }
            })
    }
}
