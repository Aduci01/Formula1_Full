package com.adamcs.formula1.data.api

import com.adamcs.formula1.data.model.DriverStandingResult
import retrofit2.http.*

interface DriverResultApi {
    @GET("/api/f1/{year}/driverStandings.json")
    suspend fun getResults(@Path("year") year: Int) : DriverStandingResult
}