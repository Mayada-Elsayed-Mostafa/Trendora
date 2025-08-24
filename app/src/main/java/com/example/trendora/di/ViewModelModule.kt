package com.example.trendora.di

import com.example.trendora.ui.home.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Product ViewModel

    //viewModelOf(::ProductViewModel)
    viewModel { ProductViewModel(repository = get()) }

    // Product Details ViewModel with parameter
    /*
    viewModel { (productId: Int) ->
        ProductDetailsViewModel(
            productId = productId,
            repository = get()
        )
    }
     */

}
