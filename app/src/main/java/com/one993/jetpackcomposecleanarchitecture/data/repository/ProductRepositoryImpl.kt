package com.one993.jetpackcomposecleanarchitecture.data.repository

import com.one993.jetpackcomposecleanarchitecture.core.ResultState
import com.one993.jetpackcomposecleanarchitecture.data.api.ProductService
import com.one993.jetpackcomposecleanarchitecture.data.dto.ProductDto
import com.one993.jetpackcomposecleanarchitecture.data.mappers.toDomain
import com.one993.jetpackcomposecleanarchitecture.data.mappers.toDomainList
import com.one993.jetpackcomposecleanarchitecture.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productService: ProductService) :
    ProductRepository {

    override suspend fun getProductList(): ResultState<List<ProductDto>> {
        return try {
            val response = productService.getAllProducts()
            if (response.isSuccessful && response.code() == 200) {
                if (response.body()?.products?.isNotEmpty() == true) {
                    val responseList = response.body()!!.toDomain()
                    ResultState.Success(responseList.productList)
                } else {
                    ResultState.Error("Products Not found")
                }
            } else {
                ResultState.Error(response.message() + response.code())
            }
        } catch (e: Exception) {
            ResultState.Error(e.localizedMessage ?: "Error in response")
        }
    }
}