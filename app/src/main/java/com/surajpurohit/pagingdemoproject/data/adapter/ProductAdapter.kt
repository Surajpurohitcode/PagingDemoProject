package com.surajpurohit.pagingdemoproject.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.surajpurohit.pagingdemoproject.R
import com.surajpurohit.pagingdemoproject.data.model.Product
import com.surajpurohit.pagingdemoproject.databinding.ProductCardBinding

class ProductAdapter(val context: Context) :
    PagingDataAdapter<Product, ProductAdapter.ViewHolder>(ProductDiffCallback()) {
    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            Glide.with(context).load(item.image).into(holder.binding.productImage)
            holder.binding.productTitle.setText(item.title)
            holder.binding.productPrice.setText("₹ ${item.price}")
            holder.binding.productRating.setText("${item.rate}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        return ViewHolder(view)
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ProductCardBinding.bind(itemView)
    }
}