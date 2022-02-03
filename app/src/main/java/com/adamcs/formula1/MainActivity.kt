package com.adamcs.formula1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adamcs.formula1.data.repository.NewsRepository
import com.adamcs.formula1.data.repository.ResultRepository
import com.adamcs.formula1.ui.theme.Formula1Theme
import com.adamcs.formula1.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*
import java.util.Objects.toString
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var newsRepository : NewsRepository

    @Inject
    lateinit var resultRepository: ResultRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadNews()

        setContent {
            Formula1Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }

    fun loadNews(){
        GlobalScope.launch(Dispatchers.IO) {
            val result = newsRepository.getNews()
            when(result) {
                is Resource.Success -> {
                    Log.d("ASD", result.data?.channel?.news.toString())
                }
                is Resource.Error -> {
                    Log.d("ASD", "News" + result.message.toString())
                }
            }
        }
    }

    fun loadResults(){
        GlobalScope.launch(Dispatchers.IO) {
            val result = resultRepository.getDriverRanking(2020)
            when(result) {
                is Resource.Success -> {
                    Log.d("ASD", result.data?.data?.standingsTable?.standingsLists?.get(0)?.driverResults.toString())

                }
                is Resource.Error -> {
                    Log.d("ASD", "Results" + result.message.toString())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Formula1Theme {
        Greeting("Android")
    }
}