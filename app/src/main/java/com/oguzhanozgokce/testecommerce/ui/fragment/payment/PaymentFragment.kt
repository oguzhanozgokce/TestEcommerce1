package com.oguzhanozgokce.testecommerce.ui.fragment.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentPaymentBinding


class PaymentFragment : Fragment() {
    lateinit var binding : FragmentPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentPaymentBinding.inflate(inflater,container,false)
        return binding.root
    }


}