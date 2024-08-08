package com.example.chatwiseassign.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatwiseassign.data.api.ProductApi
import com.example.chatwiseassign.data.repository.ProductRepository

class ProductViewModelProviderFactory(
    private val productRepository: ProductRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepository) as T
    }

}