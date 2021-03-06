package com.rasyidin.travelapp.core.di

import com.rasyidin.travelapp.BuildConfig
import com.rasyidin.travelapp.core.data.TourismRepository
import com.rasyidin.travelapp.core.data.source.remote.RemoteDataSource
import com.rasyidin.travelapp.core.data.source.remote.network.ApiService
import com.rasyidin.travelapp.core.domain.repository.ITourismRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<ITourismRepository> { TourismRepository(get()) }
}