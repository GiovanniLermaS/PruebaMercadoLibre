package com.example.pruebamercadolibre.repository

import com.example.pruebamercadolibre.api.ApiInterface
import com.example.pruebamercadolibre.db.model.SearchBySite
import com.example.pruebamercadolibre.util.SEARCH_BY_SITE
import com.example.pruebamercadolibre.util.SITES
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class ResultActivityRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getSearchBySite(siteId: String, wordSearch: String): Single<Response<SearchBySite>> {
        return apiInterface.getSearchBySite("$SITES/$siteId/$SEARCH_BY_SITE$wordSearch")
    }
}