package com.adamcs.formula1.data.repository

import com.adamcs.formula1.data.api.NewsApi
import com.adamcs.formula1.data.model.NewsList
import com.adamcs.formula1.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {

    private val TAG = "NewsRepository"

    override suspend fun getNews(): Resource<NewsList> {
        val response = try {
            api.getResults()
        } catch(e: Exception) {
            return Resource.Error(e.message!!)
        }

        return Resource.Success(response)
    }
}