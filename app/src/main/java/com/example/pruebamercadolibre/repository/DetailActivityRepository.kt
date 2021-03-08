package com.example.pruebamercadolibre.repository

import com.example.pruebamercadolibre.api.ApiInterface
import com.example.pruebamercadolibre.db.model.SearchBySite
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class DetailActivityRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getDetailByIdSearch(idSearch: String): Single<Response<SearchBySite>> {
        return apiInterface.getDetailIdBySearch(idSearch)
    }
}