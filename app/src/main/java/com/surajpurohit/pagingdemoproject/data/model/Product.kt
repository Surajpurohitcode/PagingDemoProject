package com.surajpurohit.pagingdemoproject.data.model

data class ProductResponse(
    val pages: List<Page>
)

data class Page(
    val page: Int,
    val pageSize: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<Product>
)

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val category: String
)
