package com.one993.jetpackcomposecleanarchitecture.domain.usecase

import com.one993.jetpackcomposecleanarchitecture.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * it's not necessary to put UseCase without any logic here we can safely remove this kind of use case in our app
 * */
class GetAllProductUseCase @Inject constructor(private val repository: ProductRepository){
    suspend fun execute() = repository.getProductList()
}