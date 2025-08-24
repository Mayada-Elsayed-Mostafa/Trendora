package com.example.trendora.data.local

import com.example.trendora.data.repository.IProductLocalDataSource
import com.example.trendora.model.Product

class ProductLocalDataSourceImpl : IProductLocalDataSource {

    private var cachedProducts: List<Product> = emptyList()
    private var lastCacheTime: Long = 0
    private val cacheValidityDuration = 5 * 60 * 1000L // 5 minutes

    override suspend fun getProducts(): List<Product> {
        return if (isCacheValid()) {
            cachedProducts
        } else {
            emptyList()
        }
    }

    override suspend fun saveProducts(products: List<Product>) {
        cachedProducts = products
        lastCacheTime = System.currentTimeMillis()
    }

    override suspend fun getProductById(id: Int): Product? {
        return if (isCacheValid()) {
            cachedProducts.find { it.id == id }
        } else {
            null
        }
    }

    override suspend fun clearProducts() {
        cachedProducts = emptyList()
        lastCacheTime = 0
    }

    private fun isCacheValid(): Boolean {
        return cachedProducts.isNotEmpty() &&
                (System.currentTimeMillis() - lastCacheTime) < cacheValidityDuration
    }
}