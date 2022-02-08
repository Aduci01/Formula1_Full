package com.adamcs.formula1.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adamcs.formula1.data.model.News
import com.adamcs.formula1.data.model.Race
import com.adamcs.formula1.data.repository.NewsRepository
import com.adamcs.formula1.data.repository.ScheduleRepository
import com.adamcs.formula1.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val TAG = "SCHEDULE_VIEW_MODEL"

    var raceList = mutableStateOf<List<Race>>(listOf())
    var isLoaded = mutableStateOf(false)

    init {
        getSchedule()
    }

    private fun getSchedule(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = scheduleRepository.getSchedule()) {
                is Resource.Success -> {
                    raceList.value = result.data?.data?.scheduleTable?.races!!
                    isLoaded.value = true

                    Log.d(TAG, "News fetched successfully")
                }
                is Resource.Error -> {
                    Log.d(TAG, "News" + result.message.toString())
                }
                is Resource.Loading -> {

                }
            }
        }
    }
}