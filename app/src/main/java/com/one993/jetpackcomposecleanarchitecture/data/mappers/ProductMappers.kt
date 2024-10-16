package com.one993.jetpackcomposecleanarchitecture.data.mappers

import com.one993.jetpackcomposecleanarchitecture.domain.models.Product
import com.one993.jetpackcomposecleanarchitecture.domain.models.ProductList
import com.one993.jetpackcomposecleanarchitecture.data.dto.ProductResponseDto
import com.one993.jetpackcomposecleanarchitecture.data.dto.ProductsItemDto

fun ProductResponseDto.toDomain() = ProductList(
    productList = products.toDomainList(),
    total = total ?: 0,
    skip = skip ?: 0,
    limit = limit ?: 0
)

fun List<ProductsItemDto?>?.toDomainList(): List<Product> {
    return this?.mapNotNull { it?.toDomain() } ?: emptyList()
}

fun ProductsItemDto.toDomain() = Product(
    id = id ?: 0,
    title = title.orEmpty(),
    description = description.orEmpty()
)
        