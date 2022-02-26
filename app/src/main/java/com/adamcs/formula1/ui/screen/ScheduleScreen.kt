package com.adamcs.formula1.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamcs.formula1.R
import com.adamcs.formula1.data.model.Race
import com.adamcs.formula1.ui.components.*
import com.adamcs.formula1.ui.theme.Shapes
import com.adamcs.formula1.ui.theme.gold
import com.adamcs.formula1.ui.viewmodel.ScheduleViewModel

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun ScheduleScreen(
    scheduleViewModel: ScheduleViewModel = hiltViewModel()
) {
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
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    DropDownMenu(
                        errorMessage = "Invalid year",
                        seasons = Array(20) { i -> 2022 - i }
                    ) {
                        scheduleViewModel.getSchedule(year = it)
                    }
                }
            }
        }

        ScheduleList(scheduleViewModel = scheduleViewModel)
    }
}

@ExperimentalMaterialApi
@Composable
private fun ScheduleList(
    scheduleViewModel: ScheduleViewModel
){
    if (!scheduleViewModel.isLoaded.value) {
        CircularLoadingSpinner()
    } else {
        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(12.dp))

            val finishedRaceNum = scheduleViewModel.getFinishedRacesNumber()
            ProgressBar(
                height = 22.dp,
                width = 300.dp,
                backgroundColor = Color(0.85f, 0.85f, 0.85f, 1.0f),
                foregroundColor = Brush.horizontalGradient(listOf(Color(0xffFD7D20), Color(0xffFBE41A))),
                percent = (100 * (finishedRaceNum / scheduleViewModel.raceList.value.size.toFloat())).toInt(),
                shape = Shapes.small,
                middleText = finishedRaceNum.toString() + " / " + scheduleViewModel.raceList.value.size
            )

            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(scheduleViewModel.raceList.value) { race ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ExpandableTextCard(title = "#" + race.round + " " + race.raceName) {
                            RaceContent(race = race)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

    }
}

@Composable
private fun RaceContent(
    race: Race,
    descriptionFontSize: TextUnit = 12.sp,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = race.date,
            fontSize = descriptionFontSize,
            fontWeight = descriptionFontWeight,
            lineHeight = 15.sp,
        )

        Text(
            text = race.time.dropLast(4),
            fontSize = descriptionFontSize,
            fontWeight = descriptionFontWeight,
            lineHeight = 15.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (race.circuit.circuitImageId != 0)
            Image(
                painter = painterResource(id = race.circuit.circuitImageId),
                contentDescription = "Circuit Image",
            modifier = Modifier.fillMaxWidth(.9f),
            contentScale = ContentScale.FillWidth)
    }
}