package com.one993.jetpackcomposecleanarchitecture.ui.screens

import com.one993.jetpackcomposecleanarchitecture.data.dto.ProductDto

sealed interface HomeScreenStates{
    data object Loading :HomeScreenStates
    data class Success(val list: List<ProductDto>) : HomeScreenStates
    data class Error(val message:String) : HomeScreenStates
}

