package com.example.shophai.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shophai.data.model.products.ProductsItem
import com.example.shophai.databinding.CartListItemBinding

class CartAdapter(private val products: List<ProductsItem>)
    : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(private val binding: CartListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            val productImage = binding.ivProduct
            val productTitle = binding.tvName
            val productPrice = binding.tvPrice
            val addButton = binding.btnPlus
            val subButton = binding.btnMinus
            val quantity = binding.tvCount
            val delButton = binding.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val current = products[position]

    }
}