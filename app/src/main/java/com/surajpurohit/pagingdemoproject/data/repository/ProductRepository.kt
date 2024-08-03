package com.surajpurohit.pagingdemoproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.surajpurohit.pagingdemoproject.data.api.RetrofitInstance
import com.surajpurohit.pagingdemoproject.data.paging.ProductPagingSource

class ProductRepository {

    private val apiService = RetrofitInstance.apiService

    fun getProducts() = Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 1,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ProductPagingSource(apiService) }
    ).liveData
}