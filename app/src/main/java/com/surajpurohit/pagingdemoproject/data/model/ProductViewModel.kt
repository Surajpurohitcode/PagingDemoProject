package com.surajpurohit.pagingdemoproject.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.surajpurohit.pagingdemoproject.data.repository.ProductRepository

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val products = repository.getProducts().cachedIn(viewModelScope)
}