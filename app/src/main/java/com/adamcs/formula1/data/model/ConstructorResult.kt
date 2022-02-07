package com.adamcs.formula1.data.model

import com.google.gson.annotations.SerializedName

data class ConstructorResult(
    val position: Int,
    val points: Float,
    val wins: Int,
    @SerializedName("Constructor")
    val constructor: Constructor,
)