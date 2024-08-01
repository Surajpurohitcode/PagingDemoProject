package com.surajpurohit.pagingdemoproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.surajpurohit.pagingdemoproject.data.api.ApiService
import com.surajpurohit.pagingdemoproject.data.model.Product

class ProductPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val position = params.key ?: 1
        return try {

            val response = apiService.getProducts(position, params.loadSize)
            val products = response.distinctBy { it.id }


            val nextKey = if (products.isEmpty() && position == 1 || products.size < params.loadSize) {
                null // No more data available, set nextKey to null
            } else {
                position + 1 // More data available, increment nextKey
            }


            val prevKey = if (position == 1) null else position - 1

            LoadResult.Page(
                data = products,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}





