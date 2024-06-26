package com.oguzhanozgokce.testecommerce.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.CardDesingBinding
import com.oguzhanozgokce.testecommerce.entitiy.Product
import com.oguzhanozgokce.testecommerce.ui.viewmodel.FavoritesViewModel

// Code with ♥️
// Created by Oguzhan OZGOKCE


class FavoritesAdapter(
    private val context: Context,
    private var productList: List<Product>,
    private val viewModel: FavoritesViewModel,
    private val onProductClicked: (Int) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.DesignViewHolder>() {
    inner class DesignViewHolder(val binding: CardDesingBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesignViewHolder {
        val binding = CardDesingBinding.inflate(LayoutInflater.from(context), parent, false)
        return DesignViewHolder(binding)
    }
    override fun getItemCount(): Int = productList.size
    override fun onBindViewHolder(holder: DesignViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.apply {
            // Ürün bilgilerini set et
            favoritesProductTitleID.text = product.title
            favoritesProductPriceID.text = product.price.toString()
            Glide.with(context)
                .load(product.image)
                .into(favoritesProductImageID)
        }

        holder.binding.favoritesViewID .setOnClickListener {
            onProductClicked(product.productID) // Tıklama işleyicisini çağır
        }
        // Kalp ikonunu daima dolu olarak göster
        holder.binding.favoritesFavoriteImageID.setImageResource(R.drawable.baseline_favorite_red_24)

        holder.binding.favoritesFavoriteImageID.setOnClickListener {
            val product = productList[position]
            val newFavoriteStatus = !product.isFavorite
            viewModel.updateFavoriteStatus(product.productID, newFavoriteStatus)

            // Favori listesini yeniden çek
            viewModel.fetchFavoriteProducts()
        }
    }
    fun updateData(newProducts: List<Product>) {
        productList = newProducts
        notifyDataSetChanged()
    }
}
