package com.one993.jetpackcomposecleanarchitecture.data.repository

import android.content.Context
import android.util.Log
import com.one993.jetpackcomposecleanarchitecture.core.ResultState
import com.one993.jetpackcomposecleanarchitecture.core.utils.NetworkUtils
import com.one993.jetpackcomposecleanarchitecture.data.api.ProductService
import com.one993.jetpackcomposecleanarchitecture.domain.models.Product
import com.one993.jetpackcomposecleanarchitecture.data.mappers.toDomain
import com.one993.jetpackcomposecleanarchitecture.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val context: Context

) : ProductRepository {

    override suspend fun getProductList(): ResultState<List<Product>?> {
        return try {
            Log.d("Network", "getProductList: ${NetworkUtils.isInternetAvailable(context)}")
            if (NetworkUtils.isInternetAvailable(context)) {
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
            } else {
                ResultState.Error("Please check internet connectivity")
            }

        } catch (e: Exception) {
            ResultState.Error(e.localizedMessage ?: "Error in response")
        }
    }
}