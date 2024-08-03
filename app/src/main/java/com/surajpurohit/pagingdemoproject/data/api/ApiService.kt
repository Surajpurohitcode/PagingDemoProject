package com.surajpurohit.pagingdemoproject.data.api

import com.surajpurohit.pagingdemoproject.data.model.Product
import com.surajpurohit.pagingdemoproject.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/cdb331ed-2c1d-4f30-a2ed-021fe8e18e11")
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ProductResponse
}