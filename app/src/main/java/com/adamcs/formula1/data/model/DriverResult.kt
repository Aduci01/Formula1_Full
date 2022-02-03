package com.adamcs.formula1.data.model

data class DriverResult(
    val position: Int,
    val points: Int,
    val wins: Int,
    val driver: Driver,
    val constructors: List<Constructor>
)