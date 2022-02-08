package com.adamcs.formula1.data.model

import com.google.gson.annotations.SerializedName


data class Race(
    val round: Int,
    val raceName: String,

    @SerializedName("Circuit")
    val circuit: Circuit,

    val date: String,
    val time: String
)

data class ScheduleResult(
    @SerializedName("MRData")
    val data: SchedulerMRData
)

data class SchedulerMRData (
    @SerializedName("RaceTable")
    val scheduleTable: ScheduleTable
)

data class ScheduleTable(
    @SerializedName("Races")
    val races: List<Race>
)