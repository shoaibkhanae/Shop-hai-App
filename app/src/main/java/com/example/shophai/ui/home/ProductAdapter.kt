package com.example.shophai.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shophai.R
import com.example.shophai.data.model.products.ProductsItem
import com.example.shophai.databinding.GridListItemBinding

class ProductAdapter(private val dataset: List<ProductsItem>)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(private val binding: GridListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val productImage: ImageView = binding.productImage
        fun bind(product: ProductsItem) {
            binding.productItem = product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = GridListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = dataset[position]
        holder.bind(current)

        holder.productImage.load(current.image) {
            crossfade(true)
            placeholder(R.drawable.placeholder)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }
}