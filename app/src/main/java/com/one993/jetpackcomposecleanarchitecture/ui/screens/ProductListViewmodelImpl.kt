package com.one993.jetpackcomposecleanarchitecture.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.one993.jetpackcomposecleanarchitecture.core.ResultState
import com.one993.jetpackcomposecleanarchitecture.data.dto.ProductDto
import com.one993.jetpackcomposecleanarchitecture.domain.usecase.GetAllProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductListViewmodelImpl @Inject constructor(
    private val getAllProductUseCase: GetAllProductUseCase
) : ViewModel() {

    init {
        getProductList()
    }

    private val _homeScreenStates: MutableStateFlow<HomeScreenStates> =
        MutableStateFlow(HomeScreenStates.Loading)

    val homeScreenStates = _homeScreenStates.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = HomeScreenStates.Loading
    )

    private fun getProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getAllProductUseCase.execute()
                withContext(Dispatchers.Main) {
                    handleProductResponse(response)
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main) {
                    setProductListState(HomeScreenStates.Error(e.localizedMessage ?: "Unknown error"))
                }
            }

        }
    }

    private fun handleProductResponse(response: ResultState<List<ProductDto>?>) {
        Log.d("TAG", "handleProductResponse: $response")
        when (response) {
            is ResultState.Error -> setProductListState(HomeScreenStates.Error(response.message))
            is ResultState.Success -> setProductListState(HomeScreenStates.Success(response.data?: emptyList()))
        }
    }

    private fun setProductListState(homeScreenStates: HomeScreenStates) {
        _homeScreenStates.update {
            homeScreenStates
        }
    }
}