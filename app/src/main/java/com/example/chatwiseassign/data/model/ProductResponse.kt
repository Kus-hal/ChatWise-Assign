package com.example.chatwiseassign.data.model

data class ProductResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int,
)
