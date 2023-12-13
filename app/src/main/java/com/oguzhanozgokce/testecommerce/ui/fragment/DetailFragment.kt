package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.oguzhanozgokce.testecommerce.databinding.FragmentDetailBinding
import com.oguzhanozgokce.testecommerce.domain.Product

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)


        return binding.root
    }
    //home fragmentten gelen product id alıp detail fragmenta gönderme
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailFragmentArgs by navArgs()
        val productId = args.productId

        // Burada productId'yi kullanarak ürünü bulabilir ve diğer özelliklerine erişebilirsiniz
        val product = getProductById(productId)
        if (product != null) {
            // Örnek olarak, detayları ekrana yazdıralım (örneğin ürün adı)
            binding.detailTitleID.text = product.title
            binding.detailPriceID.text = product.price.toString()
            binding.detailDescriptionID.text = product.description
            binding.detailRatingID.text = product.rating.toString()
            binding.detailImageID.setImageResource(
                resources.getIdentifier(
                    product.image,
                    "drawable",
                    requireContext().packageName
                )
            )
        }

    }
    private fun getProductById(productId: Int): Product? {
        // productId'ye göre ürünü bulma işlemi burada gerçekleştirilebilir
        // productList'ten productId'ye göre ürünü arayıp döndürebilirsiniz
        // Örnek olarak:
        val productList = getMockProductList() // Örnek bir ürün listesi alalım (bu kısmı kendi ürün listesine uygun şekilde değiştirin)
        return productList.find { it.id == productId }
    }
    private fun getMockProductList(): List<Product> {
        return listOf(
            Product(1, "Macbook", "pic1", 100.0, 9, 18.0, false, 20, "Apple", "Laptop"),
            Product(2, "Iphone", "login_image", 100.0, 9, 18.0, false, 20, "Iphone", "Laptop")
            // Diğer ürünleri de ekleyebilirsiniz
        )
    }

}