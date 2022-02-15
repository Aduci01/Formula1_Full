package com.adamcs.formula1.data.repository

import com.adamcs.formula1.data.api.ScheduleApi
import com.adamcs.formula1.data.model.ScheduleResult
import com.adamcs.formula1.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

interface ScheduleRepository {
    suspend fun getSchedule(year: Int): Resource<ScheduleResult>
}