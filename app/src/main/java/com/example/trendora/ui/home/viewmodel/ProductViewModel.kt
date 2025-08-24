package com.example.trendora.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendora.data.repository.IProductRepository
import com.example.trendora.model.Product
import com.example.trendora.ui.home.viewmodel.commands.FilterByCategoryCommand
import com.example.trendora.ui.home.viewmodel.commands.LoadCategoriesCommand
import com.example.trendora.ui.home.viewmodel.commands.LoadProductsCommand
import com.example.trendora.ui.home.viewmodel.commands.ProductCommand
import com.example.trendora.ui.home.viewmodel.commands.RefreshProductsCommand
import com.example.trendora.ui.home.viewmodel.commands.SearchProductsCommand
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*
// UI State
data class ProductUiState(
    val products: List<Product> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "All",
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val isSearching: Boolean = false
)

class ProductViewModel(
    private val repository: IProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
        loadCategories()
    }

    fun loadProducts(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            repository.getProducts(forceRefresh)
                .onSuccess { products ->
                    _uiState.value = _uiState.value.copy(
                        products = products,
                        isLoading = false
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Failed to load products"
                    )
                }
        }
    }

    fun refreshProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRefreshing = true, error = null)

            repository.getProducts(refreshFromRemote = true)
                .onSuccess { products ->
                    _uiState.value = _uiState.value.copy(
                        products = products,
                        isRefreshing = false
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isRefreshing = false,
                        error = exception.message ?: "Failed to refresh products"
                    )
                }
        }
    }

    fun searchProducts(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)

        viewModelScope.launch {
            if (query.isEmpty()) {
                loadProducts()
                return@launch
            }

            _uiState.value = _uiState.value.copy(isSearching = true, error = null)

            repository.searchProducts(query)
                .onSuccess { products ->
                    _uiState.value = _uiState.value.copy(
                        products = products,
                        isSearching = false
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isSearching = false,
                        error = exception.message ?: "Search failed"
                    )
                }
        }
    }

    fun loadCategories() {
        viewModelScope.launch {
            repository.getCategories()
                .onSuccess { categories ->
                    val allCategories = listOf("All") + categories
                    _uiState.value = _uiState.value.copy(categories = allCategories)
                }
                .onFailure { exception ->
                    // Categories loading failure is not critical
                    println("Failed to load categories: ${exception.message}")
                }
        }
    }

    fun filterByCategory(category: String) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)

        viewModelScope.launch {
            if (category == "All") {
                loadProducts()
                return@launch
            }

            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            repository.getProductsByCategory(category)
                .onSuccess { products ->
                    _uiState.value = _uiState.value.copy(
                        products = products,
                        isLoading = false
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Failed to filter products"
                    )
                }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
*/


// UI State
data class ProductUiState(
    val products: List<Product> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "All",
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val isSearching: Boolean = false
)

class ProductViewModel(
    private val repository: IProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        executeCommand(LoadProductsCommand())
        executeCommand(LoadCategoriesCommand())
    }

    private fun executeCommand(command: ProductCommand) {
        viewModelScope.launch {
            try {
                command.execute(this@ProductViewModel)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Command execution failed: ${e.message}",
                    isLoading = false,
                    isRefreshing = false,
                    isSearching = false
                )
            }
        }
    }

    fun loadProducts() {
        executeCommand(LoadProductsCommand())
    }

    fun refreshProducts() {
        executeCommand(RefreshProductsCommand())
    }

    fun searchProducts(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        executeCommand(SearchProductsCommand(query))
    }

    fun filterByCategory(category: String) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
        executeCommand(FilterByCategoryCommand(category))
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    internal suspend fun performLoadProducts(forceRefresh: Boolean = false) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        repository.getProducts(forceRefresh)
            .onSuccess { products ->
                _uiState.value = _uiState.value.copy(
                    products = products,
                    isLoading = false
                )
            }
            .onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = exception.message ?: "Failed to load products"
                )
            }
    }

    internal suspend fun performRefreshProducts() {
        _uiState.value = _uiState.value.copy(isRefreshing = true, error = null)

        repository.getProducts(forceRefresh = true)
            .onSuccess { products ->
                _uiState.value = _uiState.value.copy(
                    products = products,
                    isRefreshing = false
                )
            }
            .onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isRefreshing = false,
                    error = exception.message ?: "Failed to refresh products"
                )
            }
    }

    internal suspend fun performSearchProducts(query: String) {
        if (query.isEmpty()) {
            performLoadProducts()
            return
        }

        _uiState.value = _uiState.value.copy(isSearching = true, error = null)

        repository.searchProducts(query)
            .onSuccess { products ->
                _uiState.value = _uiState.value.copy(
                    products = products,
                    isSearching = false
                )
            }
            .onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isSearching = false,
                    error = exception.message ?: "Search failed"
                )
            }
    }

    internal suspend fun performLoadCategories() {
        repository.getCategories()
            .onSuccess { categories ->
                val allCategories = listOf("All") + categories
                _uiState.value = _uiState.value.copy(categories = allCategories)
            }
            .onFailure { exception ->
                // Categories loading failure is not critical, just log it
                println("Failed to load categories: ${exception.message}")
            }
    }

    internal suspend fun performFilterByCategory(category: String) {
        if (category == "All") {
            performLoadProducts()
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        repository.getProductsByCategory(category)
            .onSuccess { products ->
                _uiState.value = _uiState.value.copy(
                    products = products,
                    isLoading = false
                )
            }
            .onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = exception.message ?: "Failed to filter products"
                )
            }
    }
}


