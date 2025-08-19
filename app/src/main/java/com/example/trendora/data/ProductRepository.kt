package com.example.trendora.data

import com.example.trendora.model.Product
import com.example.trendora.model.Rating

class ProductRepository {

    fun getProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                title = "iPhone 15 Pro",
                price = 999.99,
                description = "Latest iPhone with amazing camera and A17 Pro chip",
                category = "electronics",
                image = "https://tse1.mm.bing.net/th/id/OIP.c-_W9Wnv4U3Uf3jaU169SQHaHa?r=0&w=1000&h=1000&rs=1&pid=ImgDetMain&o=7&rm=3",
                rating = Rating(4.5, 120)
            ),
            Product(
                id = 2,
                title = "Samsung Galaxy S24",
                price = 899.99,
                description = "Powerful Android phone with AI features",
                category = "electronics",
                image = "https://www.pricerunner.com/product/3037407366/Samsung-Galaxy-S24-Ultra-1TB.jpg",
                rating = Rating(4.3, 95)
            ),
            Product(
                id = 3,
                title = "MacBook Air M3",
                price = 1299.99,
                description = "Ultra-thin laptop with M3 chip",
                category = "computers",
                image = "https://tse4.mm.bing.net/th/id/OIP.HO9jjzt_wOXG22qLXF9esAHaFu?r=0&rs=1&pid=ImgDetMain&o=7&rm=3",
                rating = Rating(4.8, 250)
            ),
            Product(
                id = 4,
                title = "Nike Air Jordan",
                price = 179.99,
                description = "Classic basketball shoes for style and performance",
                category = "shoes",
                image = "https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/70747ab8-c070-4b1f-a718-ca4164d3f39a/jordan-23-7-shoes-VgL3Kq.png",
                rating = Rating(4.6, 89)
            )
        )
    }

}