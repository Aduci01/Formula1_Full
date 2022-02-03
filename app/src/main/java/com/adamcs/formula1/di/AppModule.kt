package com.adamcs.formula1.di

import com.adamcs.formula1.data.api.NewsApi
import com.adamcs.formula1.data.api.ResultApi
import com.adamcs.formula1.data.repository.ResultRepository
import com.adamcs.formula1.util.Constants.ERGAST_URL
import com.adamcs.formula1.util.Constants.NEWS_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDriverRankingApi(): ResultApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ERGAST_URL)
            .build()
            .create(ResultApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .baseUrl(NEWS_URL)
            .build()
            .create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideResultRepository(
        api: ResultApi
    ) = ResultRepository(api)

   /* @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_warning_24)
    )*/
}