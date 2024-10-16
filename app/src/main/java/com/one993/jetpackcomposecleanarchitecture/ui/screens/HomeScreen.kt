package com.one993.jetpackcomposecleanarchitecture.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.one993.jetpackcomposecleanarchitecture.domain.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewmodelImpl: ProductListViewmodelImpl = hiltViewModel()
) {

    val states = homeViewmodelImpl.homeScreenStates.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Blue,  // Background color
                titleContentColor = Color.White  // Text color
            ), title = {
                Text(text = "Home")
            })
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            HomeScreenContent(states.value)
        }

    }
}

@Composable
private fun HomeScreenContent(states: HomeScreenStates) {
    when (states) {
        HomeScreenStates.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is HomeScreenStates.Success -> {
            HomeScreenView(list = states.list)
        }

        is HomeScreenStates.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = states.message)
            }

        }
    }
}

@Composable
private fun HomeScreenView(list: List<Product>) {
    LazyColumn {
        itemsIndexed(items = list) { _, item ->
            HomeScreenItemView(item)
        }
    }
}

@Composable
private fun HomeScreenItemView(item: Product) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = item.title)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = item.description, fontSize = 10.sp, color = Color.Gray, lineHeight = 10.sp)
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()

    }
}
