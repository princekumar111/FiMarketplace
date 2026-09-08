package com.example.fimarketplace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ProductUiState {

    object Loading : ProductUiState()

    data class Success(
        val products: List<Product>
    ) : ProductUiState()

    data class Error(
        val message: String
    ) : ProductUiState()
}


class ProductViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow<ProductUiState>(
            ProductUiState.Loading
        )

    val uiState: StateFlow<ProductUiState> =
        _uiState.asStateFlow()


    init {
        loadProducts()
    }


    fun loadProducts() {

        viewModelScope.launch {

            try {

                // Loading state
                _uiState.value =
                    ProductUiState.Loading

                // Mock API delay
                delay(800)

                // Repository se data
                val products =
                    ProductRepository.products

                // Success state
                _uiState.value =
                    ProductUiState.Success(products)

            } catch (e: Exception) {

                // Error state
                _uiState.value =
                    ProductUiState.Error(
                        e.message
                            ?: "Something went wrong"
                    )
            }
        }
    }
}