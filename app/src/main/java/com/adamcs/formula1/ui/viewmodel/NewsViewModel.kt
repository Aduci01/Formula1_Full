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
import com.adamcs.formula1.data.repository.NewsRepository
import com.adamcs.formula1.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val TAG = "NEWS_VIEW_MODEL"

    private lateinit var newsList: List<News>
    var filteredNewsList = mutableStateOf<List<News>>(listOf())

    var isLoaded = mutableStateOf(false)

    init {
        isLoaded.value = false

        viewModelScope.launch(Dispatchers.IO) {
            getNews()
            isLoaded.value = true
        }
    }

    private suspend fun getNews() {
        when (val result = newsRepository.getNews()) {
            is Resource.Success -> {
                newsList = result.data?.channel?.news!!
                filteredNewsList.value = newsList

                Log.d(TAG, "News fetched successfully")
            }
            is Resource.Error -> {
                Log.d(TAG, "News: " + result.message.toString())
            }
            is Resource.Loading -> {

            }
        }
    }

    fun filterNews(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            filteredNewsList.value = newsList.filter {
                it.title.lowercase().contains(query) || it.description.lowercase().contains(query)
            }
        }
    }
}