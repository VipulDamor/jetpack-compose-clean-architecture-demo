package com.one993.jetpackcomposecleanarchitecture.data.repository

import com.one993.jetpackcomposecleanarchitecture.core.ResultState
import com.one993.jetpackcomposecleanarchitecture.core.utils.NetworkUtil
import com.one993.jetpackcomposecleanarchitecture.data.api.ProductService
import com.one993.jetpackcomposecleanarchitecture.data.mappers.toDomainList
import com.one993.jetpackcomposecleanarchitecture.domain.models.Product
import com.one993.jetpackcomposecleanarchitecture.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val networkUtil: NetworkUtil
) : ProductRepository {

    // here we can use flow also
    override suspend fun getProductList(): ResultState<List<Product>?> {
        return try {

            if (networkUtil.isInternetAvailable()) {
                val response = productService.getAllProducts()
                if (response.isSuccessful) {
                    ResultState.Success(response.body()?.products.toDomainList())
                } else {
                    ResultState.Error(response.message() + response.code())
                }
            } else {
                ResultState.Error("Please check internet connectivity")
                //or proceed with SQLIte database operation inCase internet not available
            }
        } catch (e: Exception) {
            ResultState.Error(e.localizedMessage ?: "Error in response")
        }
    }
}