package com.one993.jetpackcomposecleanarchitecture.data.di

import com.one993.jetpackcomposecleanarchitecture.data.api.ProductService
import com.one993.jetpackcomposecleanarchitecture.data.repository.ProductRepositoryImpl
import com.one993.jetpackcomposecleanarchitecture.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideAPiService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Provides
    fun provideProductRepository(productService: ProductService): ProductRepository {
        return ProductRepositoryImpl(productService)
    }
}