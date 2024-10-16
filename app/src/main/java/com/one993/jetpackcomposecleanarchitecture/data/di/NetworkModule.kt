package com.one993.jetpackcomposecleanarchitecture.data.di

import android.content.Context
import com.one993.jetpackcomposecleanarchitecture.core.utils.NetworkUtil
import com.one993.jetpackcomposecleanarchitecture.core.utils.NetworkUtilsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Singleton
    @Provides
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtil {
            return NetworkUtilsImpl(context)
    }
}