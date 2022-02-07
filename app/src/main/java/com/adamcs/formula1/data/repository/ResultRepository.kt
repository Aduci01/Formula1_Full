package com.adamcs.formula1.data.repository

import com.adamcs.formula1.data.api.ConstructorResultApi
import com.adamcs.formula1.data.api.DriverResultApi
import com.adamcs.formula1.data.model.ConstructorStandingResult
import com.adamcs.formula1.data.model.DriverStandingResult
import com.adamcs.formula1.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ResultRepository @Inject constructor(
    private val apiDriver: DriverResultApi,
    private val apiConstructor: ConstructorResultApi
) {

    private val TAG = "ResultRepository"

    suspend fun getDriverRanking(year: Int): Resource<DriverStandingResult> {
        val response = try {
            apiDriver.getResults(year)
        } catch(e: Exception) {
            return Resource.Error(e.message!!)
        }

        return Resource.Success(response)
    }

    suspend fun getConstructorRanking(year: Int): Resource<ConstructorStandingResult> {
        val response = try {
            apiConstructor.getResults(year)
        } catch(e: Exception) {
            return Resource.Error(e.message!!)
        }

        return Resource.Success(response)
    }
}