package com.example.trendora.data

import com.example.trendora.data.repository.IProductLocalDataSource
import com.example.trendora.data.repository.IProductRemoteDataSource
import com.example.trendora.data.repository.IProductRepository
import com.example.trendora.model.Product

class ProductRepositoryImpl(
    private val remoteDataSource: IProductRemoteDataSource,
    private val localDataSource: IProductLocalDataSource
) : IProductRepository {

    override suspend fun getProducts(refreshFromRemote: Boolean): Result<List<Product>> {
        return try {
            if (!refreshFromRemote) {
                //Local
                val localProducts = localDataSource.getProducts()
                if (localProducts.isNotEmpty()) {
                    return Result.success(localProducts)
                }
            }

            //Remote
            val remoteProducts = remoteDataSource.getProducts()
            localDataSource.saveProducts(remoteProducts)
            Result.success(remoteProducts)

        } catch (e: Exception) {

            val cachedProducts = localDataSource.getProducts()
            if (cachedProducts.isNotEmpty()) {
                Result.success(cachedProducts)
            } else {
                Result.failure(e)
            }
        }

    }

    override suspend fun getProductById(id: Int): Result<Product> {

        //See runCaching
        return try {
            localDataSource.getProductById(id)?.let {
                Result.success(it)
            }
            val product = remoteDataSource.getProductById(id)
            Result.success(product)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCategories(): Result<List<String>> {
        return try {
            val categories = remoteDataSource.getCategories()
            Result.success(categories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProductsByCategory(category: String): Result<List<Product>> {
        return kotlin.runCatching {
            remoteDataSource.getProductsByCategory(category)
        }
        /*
        return try {
            val products = remoteDataSource.getProductsByCategory(category)
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }

         */
    }

    override suspend fun searchProducts(query: String): Result<List<Product>> {
        return try {
            val products = localDataSource.getProducts().ifEmpty {
                remoteDataSource.getProducts()
            }
            val searchProducts = products.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true) ||
                        it.category.contains(query, ignoreCase = true)
            }
            Result.success(searchProducts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}