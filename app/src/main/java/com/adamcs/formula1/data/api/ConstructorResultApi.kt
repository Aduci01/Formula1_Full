package com.adamcs.formula1.data.api

import com.adamcs.formula1.data.model.ConstructorStandingResult
import com.adamcs.formula1.data.model.DriverStandingResult
import retrofit2.http.*

interface ConstructorResultApi {
    @GET("/api/f1/{year}/constructorStandings.json")
    suspend fun getResults(@Path("year") year: Int) : ConstructorStandingResult
}