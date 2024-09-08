package com.one993.jetpackcomposecleanarchitecture.data.dto

import com.one993.jetpackcomposecleanarchitecture.domain.models.ProductsItem

data class ProductListDto(
    val productList: List<ProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int

)

data class ProductDto(
    val id: Int,
    val title: String,
    val description: String
)
