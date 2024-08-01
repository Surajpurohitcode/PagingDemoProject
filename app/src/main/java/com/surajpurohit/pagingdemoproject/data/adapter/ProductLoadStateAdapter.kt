package com.surajpurohit.pagingdemoproject.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surajpurohit.pagingdemoproject.R
import com.surajpurohit.pagingdemoproject.databinding.LoadingStateBinding

class ProductLoadStateAdapter : LoadStateAdapter<ProductLoadStateAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = LoadingStateBinding.bind(itemView)

        fun bind(loadState: LoadState) {
            binding.loadingView.isVisible = loadState is LoadState.Loading
        }

    }

    override fun onBindViewHolder(
        holder: ProductLoadStateAdapter.ViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ProductLoadStateAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loading_state,parent,false)
        return ViewHolder(view)
    }
}