package com.adamcs.formula1.data.api

import com.adamcs.formula1.data.model.ScheduleResult
import retrofit2.http.GET

interface ScheduleApi {
    @GET("/api/f1/current.json")
    suspend fun getSchedule() : ScheduleResult
}