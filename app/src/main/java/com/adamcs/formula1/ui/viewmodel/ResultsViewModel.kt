package com.adamcs.formula1.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamcs.formula1.R
import com.adamcs.formula1.data.model.Constructor
import com.adamcs.formula1.data.model.ConstructorResources
import com.adamcs.formula1.data.model.ConstructorResult
import com.adamcs.formula1.data.model.DriverResult
import com.adamcs.formula1.data.repository.ResultRepository
import com.adamcs.formula1.util.DriverResourceMap.driverImageMap
import com.adamcs.formula1.util.Resource
import com.adamcs.formula1.util.TeamResourceMap.resourceMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val resultRepository: ResultRepository
) : ViewModel() {

    private val TAG = "RESULTS_VIEW_MODEL"

    var resultList = mutableStateOf<List<DriverResult>>(listOf())
    var constructorResultList = mutableStateOf<List<ConstructorResult>>(listOf())


    var isLoaded = mutableStateOf(false)

    init {
        getResults(2022)
    }

    fun getResults(year: Int) {
        isLoaded.value = false;

        viewModelScope.launch(Dispatchers.IO) {
            val driverResult = async { getDriverResults(year) }
            val constructorResult = async { getConstructorResults(year) }

            driverResult.await()
            constructorResult.await()

            isLoaded.value = true
        }
    }

    private suspend fun getDriverResults(year: Int) {
        when (val result = resultRepository.getDriverRanking(year)) {
            is Resource.Success -> {

                resultList.value =
                    result.data?.data?.standingsTable?.standingsLists?.get(0)?.driverResults!!

                loadDriverResources()

                Log.d(TAG, "Driver Results fetched successfully")
            }
            is Resource.Error -> {
                Log.d(TAG, "Driver Results" + result.message.toString())
            }
            is Resource.Loading -> {

            }
        }

    }

    private suspend fun getConstructorResults(year: Int) {
        when (val result = resultRepository.getConstructorRanking(year)) {
            is Resource.Success -> {

                constructorResultList.value =
                    result.data?.data?.standingsTable?.standingsLists?.get(0)?.constructorResults!!

                loadConstructorResources()

                Log.d(TAG, "Constructor Results fetched successfully")
            }
            is Resource.Error -> {
                Log.d(TAG, "Constructor Results" + result.message.toString())
            }
            is Resource.Loading -> {

            }
        }
    }

    private fun loadDriverResources() {
        resultList.value.forEach {
            it.driver.imageId = driverImageMap.getOrDefault(it.driver.driverId, R.drawable.missing)

            if (it.constructors.isEmpty()) {
                it.constructors.add(
                    0,
                    Constructor("none", "", "", "", ConstructorResources(Color.White, 0))
                )
            }
        }

        //Setting constructor resources
        resultList.value.forEach { result ->
            result.constructors.forEach { constructor ->
                val resource = resourceMap.getOrDefault(
                    constructor.constructorId,
                    ConstructorResources(Color.Gray, R.drawable.track)
                )
                constructor.resources = resource
            }
        }
    }

    private fun loadConstructorResources() {
        constructorResultList.value.forEach { result ->
            val resource = resourceMap.getOrDefault(
                result.constructor.constructorId,
                ConstructorResources(Color.Gray, R.drawable.track)
            )
            result.constructor.resources = resource
        }
    }
}