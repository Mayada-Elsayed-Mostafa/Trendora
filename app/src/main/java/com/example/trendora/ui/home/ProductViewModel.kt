package com.example.trendora.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendora.data.ProductRepository
import com.example.trendora.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val repository = ProductRepository()

    private val _products: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val products : StateFlow<List<Product>> = _products.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {

        viewModelScope.launch {
            _isLoading.value = true

            delay(1500)
            try {
                val productList = repository.getProducts()
                _products.value = productList
            } catch (e: Exception){
                _error.value = "Failed to load products: ${e.message}"
            } finally {
                _isLoading.value = false
            }


        }
    }

    fun refreshProducts() {
        loadProducts()
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) {
                _products.value = repository.getProducts()
            } else {
                val filteredProducts = repository.getProducts().filter {
                    it.title.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true)
                }
                _products.value = filteredProducts
            }
        }
    }

}