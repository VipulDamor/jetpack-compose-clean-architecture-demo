package com.one993.jetpackcomposecleanarchitecture.domain.usecase

import com.one993.jetpackcomposecleanarchitecture.domain.repository.ProductRepository
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(private val repository: ProductRepository){
    suspend fun execute() = repository.getProductList()
}