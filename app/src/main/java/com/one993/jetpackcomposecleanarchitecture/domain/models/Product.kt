package com.one993.jetpackcomposecleanarchitecture.domain.models

data class ProductList(
    val productList: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int

)

data class Product(
    val id: Int,
    val title: String,
    val description: String
)
