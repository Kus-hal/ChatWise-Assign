package com.example.chatwiseassign.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chatwiseassign.data.api.ProductApi
import com.example.chatwiseassign.data.model.Product
import com.example.chatwiseassign.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products
    val isLoading = MutableLiveData<Boolean>()
    private var currentPage = 1

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            isLoading.value = true
            val newProducts = repository.getProducts()
            _products.value = _products.value.orEmpty() + newProducts.orEmpty()
            isLoading.value = false
        }
    }

    fun loadMoreProducts() {
        currentPage++
        loadProducts()
    }
}