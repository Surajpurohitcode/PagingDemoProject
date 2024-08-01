package com.surajpurohit.pagingdemoproject.data.api

import com.surajpurohit.pagingdemoproject.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<Product>
}