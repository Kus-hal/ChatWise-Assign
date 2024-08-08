package com.example.chatwiseassign.data.repository

import com.example.chatwiseassign.data.api.RetrofitInstance.API
import com.example.chatwiseassign.data.model.Product

class ProductRepository(
) {
    suspend fun getProducts(): List<Product> {
        return API.getProducts().products
    }


}