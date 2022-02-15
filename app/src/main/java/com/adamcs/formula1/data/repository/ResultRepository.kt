package com.adamcs.formula1.data.repository

import com.adamcs.formula1.data.model.ConstructorStandingResult
import com.adamcs.formula1.data.model.DriverStandingResult
import com.adamcs.formula1.util.Resource

interface ResultRepository{
    suspend fun getDriverRanking(year: Int): Resource<DriverStandingResult>

    suspend fun getConstructorRanking(year: Int): Resource<ConstructorStandingResult>
}