package com.example.trendora.ui.home.viewmodel.commands

import com.example.trendora.ui.home.viewmodel.ProductViewModel


sealed interface ProductCommand {
    suspend fun execute(viewModel: ProductViewModel)
}

class LoadProductsCommand(private val forceRefresh: Boolean = false) : ProductCommand {
    override suspend fun execute(viewModel: ProductViewModel) {
        viewModel.performLoadProducts(forceRefresh)
    }
}

class RefreshProductsCommand : ProductCommand {
    override suspend fun execute(viewModel: ProductViewModel) {
        viewModel.performRefreshProducts()
    }
}

class SearchProductsCommand(private val query: String) : ProductCommand {
    override suspend fun execute(viewModel: ProductViewModel) {
        viewModel.performSearchProducts(query)
    }
}

class LoadCategoriesCommand : ProductCommand {
    override suspend fun execute(viewModel: ProductViewModel) {
        viewModel.performLoadCategories()
    }
}

class FilterByCategoryCommand(private val category: String) : ProductCommand {
    override suspend fun execute(viewModel: ProductViewModel) {
        viewModel.performFilterByCategory(category)
    }
}


