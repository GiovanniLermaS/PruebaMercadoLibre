package com.example.pruebamercadolibre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebamercadolibre.db.model.Detail
import com.example.pruebamercadolibre.repository.DetailActivityRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(private val detailActivityRepository: DetailActivityRepository) :
    ViewModel() {

    private val successDetail: MutableLiveData<ArrayList<Detail>> = MutableLiveData()
    private val errorDetail = MutableLiveData<String>()

    fun getSuccessDetail(): LiveData<ArrayList<Detail>> {
        return successDetail
    }

    fun getErrorDetail(): LiveData<String?> {
        return errorDetail
    }

    fun getDetailByIdSearch(idSearch: String) {
        detailActivityRepository.getDetailByIdSearch(idSearch).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<ArrayList<Detail>>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    errorDetail.value = e.message
                }

                override fun onSuccess(detail: Response<ArrayList<Detail>>) {
                    try {
                        successDetail.value = detail.body()
                    } catch (e: Exception) {
                        errorDetail.value =
                            "El servicio falló, vuelve a intentar"
                    }
                }
            })
    }
}
