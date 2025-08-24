package com.example.trendora.di

import com.example.trendora.data.ProductRepositoryImpl
import com.example.trendora.data.local.ProductLocalDataSourceImpl
import com.example.trendora.data.remote.ProductRemoteDataSourceImpl
import com.example.trendora.data.repository.IProductLocalDataSource
import com.example.trendora.data.repository.IProductRemoteDataSource
import com.example.trendora.data.repository.IProductRepository
import org.koin.dsl.module

val repositoryModule = module {

    // Data Sources
    single<IProductRemoteDataSource> {
        ProductRemoteDataSourceImpl(apiService = get())
    }

    single<IProductLocalDataSource> {
        ProductLocalDataSourceImpl()
    }

    // Repository
    //singleOf(::ProductRepositoryImpl ) { bind <IProductRepository>() }
    single<IProductRepository> {
        ProductRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }
}
