package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oguzhanozgokce.testecommerce.databinding.FragmentFavoritesBinding
import com.oguzhanozgokce.testecommerce.entitiy.Product
import com.oguzhanozgokce.testecommerce.ui.adapter.FavoritesAdapter
import com.oguzhanozgokce.testecommerce.ui.login.util.UserSessionManager
import com.oguzhanozgokce.testecommerce.ui.login.util.snackbar
import com.oguzhanozgokce.testecommerce.ui.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()
    @Inject
    lateinit var userSessionManager: UserSessionManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        setupRecyclerView(emptyList()) // RecyclerView'ı başlat

        viewModel.favoriteProducts.observe(viewLifecycleOwner, Observer { products ->
            // Update UI with products
            (binding.favoritesRecyclerViewID.adapter as FavoritesAdapter).updateData(products)
            // Handle visibility and empty state
            if (products.isEmpty()) {
                binding.favoritesRecyclerViewID.snackbar("No favorite products found")
                binding.favoritesRecyclerViewID.visibility = View.GONE
            } else {
                binding.favoritesRecyclerViewID.visibility = View.VISIBLE
            }
        })

        viewModel.fetchFavoriteProducts()

        binding.favoritesEditTextText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchProductsByName(s.toString())
            }
        })
        userSessionManager = UserSessionManager(requireContext())

        displayUserName()

        return binding.root
    }
    private fun setupRecyclerView(products: List<Product>) {
        val adapter = FavoritesAdapter(requireContext(), products, viewModel) { productId ->
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(productId)
            findNavController().navigate(action)
        }

        binding.favoritesRecyclerViewID.adapter = adapter
        binding.favoritesRecyclerViewID.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun displayUserName() {
        val name = userSessionManager.getUserName()
        val surname = userSessionManager.getUserSurname()
        binding.favoritesCustomNameID.text = "$name $surname"
    }
}
