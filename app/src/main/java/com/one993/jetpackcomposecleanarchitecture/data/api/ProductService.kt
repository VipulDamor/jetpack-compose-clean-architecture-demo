package com.one993.jetpackcomposecleanarchitecture.data.api

import com.one993.jetpackcomposecleanarchitecture.domain.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getAllProducts(): Response<ProductResponse>

}