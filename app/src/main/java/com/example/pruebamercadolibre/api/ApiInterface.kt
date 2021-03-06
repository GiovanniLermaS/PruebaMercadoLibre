package com.example.pruebamercadolibre.api

import com.example.pruebamercadolibre.db.model.SearchBySite
import com.example.pruebamercadolibre.db.model.Site
import com.example.pruebamercadolibre.util.SITES
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET(SITES)
    fun getSites(
    ): Single<Response<ArrayList<Site>>>

    @GET
    fun getSearchBySite(
        @Url searchBySite: String,
    ): Single<Response<SearchBySite>>
}