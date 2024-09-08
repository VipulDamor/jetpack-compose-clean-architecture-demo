package com.one993.jetpackcomposecleanarchitecture.domain.repository

import com.one993.jetpackcomposecleanarchitecture.core.ResultState
import com.one993.jetpackcomposecleanarchitecture.data.dto.ProductDto

interface ProductRepository {
    suspend fun getProductList(): ResultState<List<ProductDto>>
}