package com.oguzhanozgokce.testecommerce.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.testecommerce.databinding.CardDesingBinding
import com.oguzhanozgokce.testecommerce.domain.Produckt

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------     |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|

class ProductAdapter(
    var mContex: Context,
    var productList: List<Produckt>,
) :
    RecyclerView.Adapter<ProductAdapter.DesingViewHolder>() {
    inner class DesingViewHolder(var binding: CardDesingBinding) :
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
    }
}