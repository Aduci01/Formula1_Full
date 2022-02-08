package com.adamcs.formula1.data.api

import com.adamcs.formula1.data.model.ScheduleResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleApi {
    @GET("/api/f1/{year}.json")
    suspend fun getSchedule(@Path("year") year: Int) : ScheduleResult
}