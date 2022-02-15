package com.adamcs.formula1.di

import com.adamcs.formula1.data.api.ConstructorResultApi
import com.adamcs.formula1.data.api.NewsApi
import com.adamcs.formula1.data.api.DriverResultApi
import com.adamcs.formula1.data.api.ScheduleApi
import com.adamcs.formula1.data.repository.*
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
    fun provideDriverRankingApi(): DriverResultApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ERGAST_URL)
            .build()
            .create(DriverResultApi::class.java)
    }

    @Singleton
    @Provides
    fun provideConstructorRankingApi(): ConstructorResultApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ERGAST_URL)
            .build()
            .create(ConstructorResultApi::class.java)
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
    fun provideScheduleApi(): ScheduleApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ERGAST_URL)
            .build()
            .create(ScheduleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideResultRepository(
        apiDriver: DriverResultApi,
        apiConstructor: ConstructorResultApi
    ) : ResultRepository = ResultRepositoryImpl(apiConstructor = apiConstructor, apiDriver = apiDriver)

    @Singleton
    @Provides
    fun provideNewsRepository(
        api: NewsApi
    ) : NewsRepository = NewsRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideScheduleRepository(
        api: ScheduleApi
    ) : ScheduleRepository = ScheduleRepositoryImpl(api)
}