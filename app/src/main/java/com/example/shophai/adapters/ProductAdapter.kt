package com.example.shophai.adapters

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

    inner class ProductViewHolder(private val binding: GridListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val productImage: ImageView = binding.productImage
        fun bind(product: ProductsItem) {
            binding.apply {
                productName.text = product.title
                productPrice.text = product.price.toString()
                productRating.text = product.rating.rate.toString()
                productReviews.text = product.rating.count.toString()
                productImage.apply {
                    transitionName = product.image
                    load(product.image) {
                        crossfade(true)
                        placeholder(R.drawable.placeholder)
                    }
                }
                cdProduct.setOnClickListener {
                    productSelectedListener.productSelected(product, productImage)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = GridListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    interface ProductSelectedListener {
        fun productSelected(product: ProductsItem, imageView: ImageView)
    }

    lateinit var productSelectedListener : ProductSelectedListener

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = dataset[position]
        holder.bind(current)

//        holder.itemView.setOnClickListener {
//            onItemClickListener?.onItemClick(position)
//        }
    }
}