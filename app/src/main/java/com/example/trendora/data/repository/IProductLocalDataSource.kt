package com.example.trendora.data.repository

import com.example.trendora.model.Product

interface IProductLocalDataSource {
    suspend fun getProducts(): List<Product>
    suspend fun saveProducts(products: List<Product>)
    suspend fun getProductById(id: Int): Product?
    suspend fun clearProducts()
}