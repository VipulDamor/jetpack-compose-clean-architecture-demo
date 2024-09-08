package com.one993.jetpackcomposecleanarchitecture.data.mappers

import com.one993.jetpackcomposecleanarchitecture.data.dto.ProductDto
import com.one993.jetpackcomposecleanarchitecture.data.dto.ProductListDto
import com.one993.jetpackcomposecleanarchitecture.domain.models.ProductResponse
import com.one993.jetpackcomposecleanarchitecture.domain.models.ProductsItem

fun ProductResponse.toDomain() = ProductListDto(
    productList = products.toDomainList(),
    total = total ?: 0,
    skip = skip ?: 0,
    limit = limit ?: 0
)

fun List<ProductsItem?>?.toDomainList(): List<ProductDto> {
    return this?.mapNotNull { it?.toDomain() } ?: emptyList()
}

fun ProductsItem.toDomain() = ProductDto(
    id = id ?: 0,
    title = title.orEmpty(),
    description = description.orEmpty()
)
        