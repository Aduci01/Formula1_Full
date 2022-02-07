package com.adamcs.formula1.data.model

import com.google.gson.annotations.SerializedName

data class DriverResult(
    val position: Int,
    val points: Float,
    val wins: Int,
    @SerializedName("Driver")
    val driver: Driver,
    @SerializedName("Constructors")
    val constructors: MutableList<Constructor>,
)