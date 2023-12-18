package com.oguzhanozgokce.testecommerce.ui.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.CardDesingBinding
import com.oguzhanozgokce.testecommerce.entitiy.Product
import com.oguzhanozgokce.testecommerce.ui.viewmodel.HomeViewModel

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------     |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|

class ProductAdapter(
    val mContex: Context,
    var productList: List<Product>,
    val viewModel: HomeViewModel,
    private val onProductClicked: (Int) -> Unit

) :
    RecyclerView.Adapter<ProductAdapter.DesingViewHolder>() {
    inner class DesingViewHolder(var binding: CardDesingBinding) :  // inner class ile inner class ın dış sınıfın özelliklerine erişmesini sağlar.
        RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesingViewHolder {
        val binding = CardDesingBinding.inflate(LayoutInflater.from(mContex), parent, false)
        return  DesingViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: DesingViewHolder, position: Int) {
        // veritabanından gelen ürünlerin gösterilmesi
            val product = productList[position]
            Glide.with(mContex)
                .load(product.image) // Resmin dosya yolunu yükle
                .into(holder.binding.favoritesProductImageID) // ImageView'e yükle
            holder.binding.cardDetailRatingID.text = product.rating.toString()
            holder.binding.favoritesProductTitleID.text = product.title
            holder.binding.favoritesProductPriceID.text = product.price.toString()


        holder.binding.favoritesFavoriteImageID.setOnClickListener {
            // Favori durumunu tersine çevir
            val product = productList[position]
            val newFavoriteStatus = !product.isFavorite
            viewModel.updateFavoriteStatus(product.productID, newFavoriteStatus)

            // Lokal listeyi güncelle
            product.isFavorite = newFavoriteStatus

            // UI'da kalp ikonunu güncelle
            if (newFavoriteStatus) {
                holder.binding.favoritesFavoriteImageID.setImageResource(R.drawable.baseline_favorite_red_24)
            } else {
                holder.binding.favoritesFavoriteImageID.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }

        holder.binding.favoritesViewID.setOnClickListener {
            onProductClicked(product.productID)
        }

    }
    fun updateData(newProducts: List<Product>) {
        productList = newProducts
        notifyDataSetChanged() // Listeyi güncelledikten sonra bu çağrıyı yapın
    }
}