package com.adamcs.formula1.data.model

import androidx.compose.ui.graphics.Color

data class Constructor(
    val constructorId: String,
    val url: String,
    val name: String,
    val nationality: String,

    var resources: ConstructorResources
)

data class ConstructorResources(
    val color: Color,
    val logoId: Int,
)