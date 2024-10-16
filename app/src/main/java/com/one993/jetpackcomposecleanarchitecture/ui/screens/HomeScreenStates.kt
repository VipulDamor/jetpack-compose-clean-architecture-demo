package com.one993.jetpackcomposecleanarchitecture.ui.screens

import com.one993.jetpackcomposecleanarchitecture.domain.models.Product

sealed interface HomeScreenStates{
    data object Loading :HomeScreenStates
    data class Success(val list: List<Product>) : HomeScreenStates
    data class Error(val message:String) : HomeScreenStates
}

