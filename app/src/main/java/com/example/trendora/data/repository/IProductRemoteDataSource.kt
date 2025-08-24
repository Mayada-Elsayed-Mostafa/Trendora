package com.example.trendora.data.repository

import com.example.trendora.model.Product

interface IProductRemoteDataSource {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(id: Int): Product
    suspend fun getCategories(): List<String>
    suspend fun getProductsByCategory(category: String): List<Product>
}