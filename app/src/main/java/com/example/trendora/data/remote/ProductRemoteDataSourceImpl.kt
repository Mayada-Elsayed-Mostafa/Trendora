package com.example.trendora.data.remote

import com.example.trendora.data.remote.api.ProductApiService
import com.example.trendora.data.remote.dto.toDomain
import com.example.trendora.data.repository.IProductRemoteDataSource
import com.example.trendora.model.Product
import com.example.trendora.utils.NetworkException

class ProductRemoteDataSourceImpl(
    private val apiService: ProductApiService
) : IProductRemoteDataSource {

    override suspend fun getProducts(): List<Product> {
        return try {
            apiService.getProducts().toDomain()
        } catch (e: Exception) {
            throw NetworkException("Failed to fetch products: ${e.message}", e)
        }
    }

    override suspend fun getProductById(id: Int): Product {
        return try {
            apiService.getProductById(id).toDomain()
        } catch (e: Exception) {
            throw NetworkException("Failed to fetch product with id $id: ${e.message}", e)
        }
    }

    override suspend fun getCategories(): List<String> {
        return try {
            apiService.getCategories()
        } catch (e: Exception) {
            throw NetworkException("Failed to fetch categories: ${e.message}", e)
        }
    }

    override suspend fun getProductsByCategory(category: String): List<Product> {
        return try {
            apiService.getProductsByCategory(category).toDomain()
        } catch (e: Exception) {
            throw NetworkException(
                "Failed to fetch products for category $category: ${e.message}",
                e
            )
        }
    }
}