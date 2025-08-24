package com.example.trendora.data.repository

import com.example.trendora.model.Product

interface IProductRepository {
    suspend fun getProducts(forceRefresh: Boolean = false): Result<List<Product>>
    suspend fun getProductById(id: Int): Result<Product>
    suspend fun getCategories(): Result<List<String>>
    suspend fun getProductsByCategory(category: String): Result<List<Product>>
    suspend fun searchProducts(query: String): Result<List<Product>>
}