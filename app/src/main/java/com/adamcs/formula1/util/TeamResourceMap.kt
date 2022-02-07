package com.adamcs.formula1.util

import androidx.compose.ui.graphics.Color
import com.adamcs.formula1.R
import com.adamcs.formula1.data.model.ConstructorResources

object TeamResourceMap {
    private val mercedes = ConstructorResources(color = Color(0xFF00CFBA), logoId = R.drawable.mercedes)
    private val ferrari = ConstructorResources(color = Color(0xFFC30000), logoId = R.drawable.ferrari)
    private val redbull = ConstructorResources(color = Color(0xFF0000BD), logoId = R.drawable.red_bull)
    private val racingPoint = ConstructorResources(color = Color(0xFFF48Fb1), logoId = R.drawable.racing_point)
    private val renault = ConstructorResources(color = Color(0xFFFFD800), logoId = R.drawable.renault)
    private val mclaren = ConstructorResources(color = Color(0xFFFF8700), logoId = R.drawable.mclaren)
    private val alphatauri = ConstructorResources(color = Color(0xFF2B4562), logoId = R.drawable.alphatauri)
    private val alfa = ConstructorResources(color = Color(0xFF900000), logoId = R.drawable.alfa)
    private val haas = ConstructorResources(color = Color(0xFFFFFFFF), logoId = R.drawable.haas)
    private val williams = ConstructorResources(color = Color(0xFF005AFF), logoId = R.drawable.williams)
    private val alpine = ConstructorResources(color = Color(0xFF00CFBA), logoId = R.drawable.alpine)
    private val force_india = ConstructorResources(color = Color(0xFFF48Fb1), logoId = R.drawable.force_india)
    private val lotus_f1 = ConstructorResources(color = Color(0xFFFFB800), logoId = R.drawable.lotus_f1_team_logo)
    private val toro_rosso = ConstructorResources(color = Color(0xFF0000FF), logoId = R.drawable.toro_rosso_logo)
    private val sauber = ConstructorResources(color = Color(0xFF006EFF), logoId = R.drawable.sauber_f1_team)
    private val manor = ConstructorResources(color = Color(0xFFAAFFFF), logoId = R.drawable.manor1)
    private val aston_martin = ConstructorResources(color = Color(0xFF006F62), logoId = R.drawable.aston_martin)

    val resourceMap = mapOf(
        "mercedes" to mercedes,
        "ferrari" to ferrari,
        "red_bull" to redbull,
        "racing_point" to racingPoint,
        "renault" to renault,
        "mclaren" to mclaren,
        "alphatauri" to alphatauri,
        "alfa" to alfa,
        "haas" to haas,
        "williams" to williams,
        "alpine" to alpine,
        "force_india" to force_india,
        "lotus_f1" to lotus_f1,
        "toro_rosso" to toro_rosso,
        "sauber" to sauber,
        "manor" to manor,
        "aston_martin" to aston_martin,
        )

}