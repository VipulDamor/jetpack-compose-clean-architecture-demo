package com.one993.jetpackcomposecleanarchitecture.repository

import com.one993.jetpackcomposecleanarchitecture.core.ResultState
import com.one993.jetpackcomposecleanarchitecture.core.utils.NetworkUtil
import com.one993.jetpackcomposecleanarchitecture.data.api.ProductService
import com.one993.jetpackcomposecleanarchitecture.data.dto.ProductResponseDto
import com.one993.jetpackcomposecleanarchitecture.data.dto.ProductsItemDto
import com.one993.jetpackcomposecleanarchitecture.data.repository.ProductRepositoryImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryTest {

    @Test
    fun returns_success_when_network_available_and_api_response_successful() = runBlocking {
        // Arrange
        val productService = mock<ProductService>()
        val networkUtil = mock<NetworkUtil>()
        val productRepository = ProductRepositoryImpl(productService, networkUtil)
        val productResponseDto = ProductResponseDto(
            products = listOf(
                ProductsItemDto(
                    title = "Product 1",
                    description = "Description 1"
                )
            )
        )
        val response = Response.success(productResponseDto)

        whenever(networkUtil.isInternetAvailable()).thenReturn(true)
        whenever(productService.getAllProducts()).thenReturn(response)

        // Act
        val result = productRepository.getProductList()

        // Assert
        assertTrue(result is ResultState.Success)
        assertEquals(1, (result as ResultState.Success).data?.size)
    }

    // Handles null product list in API response gracefully
    @Test
    fun handles_null_product_list_in_api_response_gracefully() = runBlocking {
        // Arrange
        val productService = mock<ProductService>()
        val networkUtil = mock<NetworkUtil>()
        val productRepository = ProductRepositoryImpl(productService, networkUtil)
        val productResponseDto = ProductResponseDto(null)
        val response = Response.success(productResponseDto)

        whenever(networkUtil.isInternetAvailable()).thenReturn(true)
        whenever(productService.getAllProducts()).thenReturn(response)

        // Act
        val result = productRepository.getProductList()

        // Assert
        assertTrue(result is ResultState.Success)
        assertTrue((result as ResultState.Success).data?.isEmpty() == true)
    }
}

