package com.example.chatwiseassign.data.api

import com.example.chatwiseassign.data.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}


