package com.example.trendora.di

import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    //okHttp

    // Retrofit
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .client(get())
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
    }

}