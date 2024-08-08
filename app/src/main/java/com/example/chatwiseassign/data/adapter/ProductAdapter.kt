package com.example.chatwiseassign.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatwiseassign.data.model.Product
import com.example.chatwiseassign.databinding.ProductItemLayoutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductAdapter(
    private val productList: ArrayList<Product>,
    private val onItemClick: (View, Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    //    private val list: ArrayList<Product> = arrayListOf()
    inner class ProductViewHolder(
        val binding: ProductItemLayoutBinding,
        onItemClicked: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding =
            ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding) {
            onItemClick(binding.ivProductImage,productList[it])
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        // Bind the data to the views using the binding object
        holder.binding.tvTitle.text = product.title
        holder.binding.tvDescription.text = product.description
        holder.binding.tvPrice.text = product.price.toString()
        holder.binding.tvBrand.text = product.brand
        holder.binding.rbRating.rating = product.rating.toFloat()
        Glide.with(holder.itemView.context)
            .load(product.images[0])
            .into(holder.binding.ivProductImage)
    }

    override fun getItemCount(): Int = productList.size
    suspend fun update(products: List<Product>) {
        withContext(Dispatchers.Main){
            productList.addAll(products)
            notifyDataSetChanged()
        }
    }


}