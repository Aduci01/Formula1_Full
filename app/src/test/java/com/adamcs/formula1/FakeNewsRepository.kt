package com.adamcs.formula1

import com.adamcs.formula1.data.model.NewsList
import com.adamcs.formula1.data.repository.NewsRepository
import com.adamcs.formula1.util.Resource

class FakeNewsRepository : NewsRepository {
    override suspend fun getNews(): Resource<NewsList> {
        return Resource.Loading()
    }
}