package com.adamcs.formula1.data.repository

import android.util.Log
import com.adamcs.formula1.data.api.ResultApi
import com.adamcs.formula1.data.model.DriverStandingResult
import com.adamcs.formula1.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class ResultRepository @Inject constructor(
    private val api: ResultApi
) {

    private val TAG = "ResultRepository"

    /*fun getDriverRanking(year: Int):  {
        api.getResults(year).enqueue(object : Callback<DriverStandingResult?> {
            override fun onResponse(
                call: Call<DriverStandingResult?>,
                response: Response<DriverStandingResult?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {  }
                } else {
                    Log.d(TAG, "Error: " + response.message())
                }
            }

            override fun onFailure(
                call: Call<DriverStandingResult?>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()

                var errorMsg = "Network request error occured, check LOG"
                Log.d(TAG, errorMsg)
            }
        })
    }*/

    suspend fun getDriverRanking(year: Int): Resource<DriverStandingResult> {
        val response = try {
            api.getResults(year)
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }

        return Resource.Success(response)
    }
}