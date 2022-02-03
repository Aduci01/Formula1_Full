package com.adamcs.formula1.data.api

import com.adamcs.formula1.data.model.NewsList
import retrofit2.http.*

interface NewsApi {
    @GET("/content/fom-website/en/latest/all.xml")
    suspend fun getResults() : NewsList
}