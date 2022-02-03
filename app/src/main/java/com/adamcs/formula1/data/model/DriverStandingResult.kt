package com.adamcs.formula1.data.model

import com.google.gson.annotations.SerializedName

data class DriverStandingResult(
    @SerializedName("MRData")
    val data: MRData
)

data class MRData (
    @SerializedName("StandingsTable")
    val standingsTable: StandingsTable
)

data class StandingsTable(
    @SerializedName("StandingsLists")
    val standingsLists: List<StandingsList>
)

data class StandingsList(
    @SerializedName("DriverStandings")
    val driverResults: List<DriverResult>
)