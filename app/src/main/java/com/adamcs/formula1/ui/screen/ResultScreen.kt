package com.adamcs.formula1.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenu
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamcs.formula1.R
import com.adamcs.formula1.ui.components.*
import com.adamcs.formula1.ui.theme.Shapes
import com.adamcs.formula1.ui.theme.gold
import com.adamcs.formula1.ui.viewmodel.ResultsViewModel

@Composable
fun ResultScreen(
    resultModel: ResultsViewModel = hiltViewModel()
) {
    var isDriver by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CurvedHeader(){
            Box(modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(1f)) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    DropDownMenu(
                        errorMessage = "Invalid year",
                        seasons = Array(63) { i -> 2022 - i }
                    ) {
                        resultModel.getResults(year = it)
                    }

                    SwitchButtons(
                        isDriver = isDriver
                    ){
                        isDriver = it
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        if (isDriver){
            DriverStandingUi(resultModel = resultModel)
        } else {
            ConstructorStandingUi(resultModel = resultModel)
        }
    }
}

@Composable
private fun SwitchButtons(
    isDriver: Boolean,
    onClick: (Boolean) -> Unit = {}
) {
    val disabledColor = Color.Gray
    val enabledColor = gold

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Drivers",
            modifier = Modifier
                .clickable {
                    onClick(true)
                },
            color = if (isDriver) enabledColor else disabledColor
        )

        Text(
            text = "Constructors",
            modifier = Modifier
                .clickable {
                    onClick(false)
                },
            color = if (isDriver) disabledColor else enabledColor
        )
    }
}

@Composable
private fun DriverStandingUi(resultModel: ResultsViewModel){
    if (!resultModel.isLoaded.value) {
        CircularLoadingSpinner()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black, shape = Shapes.small)
                .padding(top = 5.dp),
            contentPadding = PaddingValues(top = 25.dp)
        ) {
            items(resultModel.resultList.value) { result ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    RankingCard(driverResult = result, height = 150)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun ConstructorStandingUi(resultModel: ResultsViewModel){
    if (!resultModel.isLoaded.value) {
        CircularLoadingSpinner()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black, shape = Shapes.small)
                .padding(top = 5.dp),
            contentPadding = PaddingValues(top = 25.dp)
        ) {
            items(resultModel.constructorResultList.value) { result ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ConstructorRankingCard(constructorResult = result, height = 120)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
@Preview
fun ResultScreenPreview() {
    ResultScreen()
}