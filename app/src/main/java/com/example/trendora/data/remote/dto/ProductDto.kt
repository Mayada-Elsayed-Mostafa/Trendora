package com.example.trendora.data.remote.dto

import com.example.trendora.model.Product
import com.example.trendora.model.Rating

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingDto
)

data class RatingDto(
    val rate: Double,
    val count: Int
)

// Extension function to convert DTO to Domain Model
fun ProductDto.toDomain(): Product = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = Rating(rating.rate, rating.count)
)

// Convert list of DTOs
fun List<ProductDto>.toDomain(): List<Product> = map { it.toDomain() }
