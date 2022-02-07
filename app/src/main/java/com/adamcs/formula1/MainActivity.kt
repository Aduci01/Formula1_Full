package com.adamcs.formula1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.adamcs.formula1.ui.screen.MainScreen
import com.adamcs.formula1.ui.theme.Formula1Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Formula1Theme {
                MainScreen()
            }
        }
    }

    /*fun loadResults(){
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
    }*/
}