package com.jay.randomuser.modules

import com.jay.randomuser.BuildConfig
import com.jay.randomuser.data.api.RemoteApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        OkHttpClient().newBuilder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single { RxJava2CallAdapterFactory.create() }

    single { GsonConverterFactory.create() }

    single {
        Retrofit.Builder()
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .addConverterFactory(get<GsonConverterFactory>())
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .build()
    }

    single { get<Retrofit>().create(RemoteApi::class.java) }

}