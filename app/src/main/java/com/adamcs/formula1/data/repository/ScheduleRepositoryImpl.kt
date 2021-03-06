package com.adamcs.formula1.data.repository

import com.adamcs.formula1.data.api.ScheduleApi
import com.adamcs.formula1.data.model.ScheduleResult
import com.adamcs.formula1.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ScheduleRepositoryImpl @Inject constructor(
    private val api: ScheduleApi
) :ScheduleRepository {

    private val TAG = "ScheduleRepository"

    override suspend fun getSchedule(year: Int): Resource<ScheduleResult> {
        val response = try {
            api.getSchedule(year)
        } catch(e: Exception) {
            return Resource.Error(e.message!!)
        }

        return Resource.Success(response)
    }
}