package com.adamcs.formula1.ui.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adamcs.formula1.R
import com.adamcs.formula1.data.model.ConstructorResources
import com.adamcs.formula1.data.model.News
import com.adamcs.formula1.data.model.Race
import com.adamcs.formula1.data.repository.NewsRepository
import com.adamcs.formula1.data.repository.ScheduleRepository
import com.adamcs.formula1.util.Resource
import com.adamcs.formula1.util.TeamResourceMap
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
    @ApplicationContext val context: Context
) : ViewModel() {


    private val TAG = "SCHEDULE_VIEW_MODEL"

    var raceList = mutableStateOf<List<Race>>(listOf())
    var isLoaded = mutableStateOf(false)

    init {
        getSchedule(2022)
    }

    fun getSchedule(year: Int) {
        isLoaded.value = false

        viewModelScope.launch(Dispatchers.IO) {
            fetchSchedule(year)

            isLoaded.value = true
        }
    }

    private suspend fun fetchSchedule(year: Int) {
        when (val result = scheduleRepository.getSchedule(year)) {
            is Resource.Success -> {
                raceList.value = result.data?.data?.scheduleTable?.races!!
                isLoaded.value = true

                loadResources()

                Log.d(TAG, "Schedule fetched successfully")
            }
            is Resource.Error -> {
                Log.d(TAG, "Schedule" + result.message.toString())
            }
            is Resource.Loading -> {

            }
        }
    }

    private fun loadResources() {
        raceList.value.forEach { race ->
            val id: Int = context.resources.getIdentifier(
                "circuit_" + race.circuit.circuitId.lowercase(),
                "drawable",
                context.packageName
            )

            race.circuit.circuitImageId = id
        }
    }

    fun getFinishedRacesNumber(): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var raceNum = 0
            val currentDate = LocalDateTime.now()

            raceList.value.forEach { race ->
                val raceDate = LocalDateTime.parse(
                    race.date + ":" + race.time.dropLast(1),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")
                )

                if (currentDate.isAfter(raceDate)) {
                    raceNum++
                }
            }

            return raceNum
        } else {
            return 0
        }
    }
}