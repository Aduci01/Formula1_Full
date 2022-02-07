package com.adamcs.formula1.data.model

import com.google.gson.annotations.SerializedName

data class ConstructorStandingResult(
    @SerializedName("MRData")
    val data: ConstructorMRData
)

data class ConstructorMRData (
    @SerializedName("StandingsTable")
    val standingsTable: ConstructorStandingsTable
)

data class ConstructorStandingsTable(
    @SerializedName("StandingsLists")
    val standingsLists: List<ConstructorStandingsList>
)

data class ConstructorStandingsList(
    @SerializedName("ConstructorStandings")
    val constructorResults: List<ConstructorResult>
)
