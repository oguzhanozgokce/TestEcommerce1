package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oguzhanozgokce.testecommerce.databinding.FragmentFavoritesBinding
import com.oguzhanozgokce.testecommerce.entitiy.Product
import com.oguzhanozgokce.testecommerce.ui.adapter.FavoritesAdapter
import com.oguzhanozgokce.testecommerce.ui.adapter.ProductAdapter
import com.oguzhanozgokce.testecommerce.ui.login.util.snackbar
import com.oguzhanozgokce.testecommerce.ui.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        setupRecyclerView(emptyList()) // RecyclerView'ı başlat

        viewModel.favoriteProducts.observe(viewLifecycleOwner, Observer { products ->
            if (products.isEmpty()) {
                // Boş liste mesajını göster
                binding.favoritesRecyclerViewID.snackbar("No favorite products yet")
                binding.favoritesRecyclerViewID.visibility = View.GONE
            } else {
                // Ürünleri güncelle ve göster
                (binding.favoritesRecyclerViewID.adapter as FavoritesAdapter).updateData(products)
                binding.favoritesRecyclerViewID.visibility = View.VISIBLE
            }
        })
        viewModel.fetchFavoriteProducts()
        return binding.root
    }
    private fun setupRecyclerView(products: List<Product>) {
        val adapter = FavoritesAdapter(requireContext(), products, viewModel) { productId ->
            // Tıklama işleyicisi burada tanımlanıyor
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(productId)
            findNavController().navigate(action)
        }

        binding.favoritesRecyclerViewID.adapter = adapter
        binding.favoritesRecyclerViewID.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }
}
