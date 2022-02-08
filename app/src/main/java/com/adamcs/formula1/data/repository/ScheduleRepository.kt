package com.adamcs.formula1.data.repository

import com.adamcs.formula1.data.api.ScheduleApi
import com.adamcs.formula1.data.model.ScheduleResult
import com.adamcs.formula1.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ScheduleRepository @Inject constructor(
    private val api: ScheduleApi
) {

    private val TAG = "NewsRepository"

    suspend fun getSchedule(year: Int): Resource<ScheduleResult> {
        val response = try {
            api.getSchedule(year)
        } catch(e: Exception) {
            return Resource.Error(e.message!!)
        }

        return Resource.Success(response)
    }
}