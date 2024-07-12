package com.example.shophai.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shophai.R
import com.example.shophai.data.model.productsItem

class ProductAdapter(private val dataset: ArrayList<productsItem>)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.product_image)
        val productCategory: TextView = view.findViewById(R.id.product_category)
        val productType: TextView = view.findViewById(R.id.product_type)
        val productName: TextView = view.findViewById(R.id.product_name)
        val productPrice: TextView = view.findViewById(R.id.product_price)
        val productRatting: TextView = view.findViewById(R.id.ratting)
        val productReviews: TextView = view.findViewById(R.id.reviews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_list_item,parent,false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = dataset[position]
        holder.productImage.load(current.image)
        holder.productCategory.text = current.category
        holder.productName.text = current.title
        holder.productPrice.text = current.price.toString()
        holder.productRatting.text = current.rating.rate.toString()
        holder.productReviews.text = current.rating.count.toString()
    }
}