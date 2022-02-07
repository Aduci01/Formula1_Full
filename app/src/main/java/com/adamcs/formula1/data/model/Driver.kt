package com.adamcs.formula1.data.model


data class Driver(
    val driverId: String,
    val code: String,
    val permanentNumber: Int,
    val url: String,
    val givenName: String,
    val familyName: String,
    val dateOfBirth: String,
    val nationality: String,

    var imageId: Int
)