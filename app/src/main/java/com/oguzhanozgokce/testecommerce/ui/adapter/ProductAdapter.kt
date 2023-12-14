package com.oguzhanozgokce.testecommerce.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.testecommerce.databinding.CardDesingBinding
import com.oguzhanozgokce.testecommerce.domain.Product
import com.oguzhanozgokce.testecommerce.ui.viewmodel.HomeViewModel

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------     |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|

class ProductAdapter(
    var mContex: Context,
    var productList: List<Product>,
    var viewModel: HomeViewModel

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
        val product = productList[position]
        holder.binding.favoritesProductImageID.setImageResource(
            mContex.resources.getIdentifier(
                product.image,
                "drawable",
                mContex.packageName
            )
        )
        holder.binding.favoritesProductTitleID.text = product.title
        holder.binding.favoritesProductPriceID.text = product.price.toString()

    }
}