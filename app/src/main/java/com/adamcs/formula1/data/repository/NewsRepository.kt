package com.adamcs.formula1.data.repository

import com.adamcs.formula1.data.model.NewsList
import com.adamcs.formula1.util.Resource

interface NewsRepository {
    suspend fun getNews(): Resource<NewsList>
}