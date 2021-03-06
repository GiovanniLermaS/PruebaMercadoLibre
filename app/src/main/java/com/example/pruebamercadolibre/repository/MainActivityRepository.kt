package com.example.pruebamercadolibre.repository

import com.example.pruebamercadolibre.api.ApiInterface
import com.example.pruebamercadolibre.db.model.Site
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MainActivityRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getSites(): Single<Response<ArrayList<Site>>> {
        return apiInterface.getSites()
    }
}