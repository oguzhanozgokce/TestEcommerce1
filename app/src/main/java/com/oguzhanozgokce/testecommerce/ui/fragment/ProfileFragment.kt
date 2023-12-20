package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentProfileBinding
import com.oguzhanozgokce.testecommerce.ui.login.util.UserSessionManager
import com.oguzhanozgokce.testecommerce.ui.login.util.loginPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var userSessionManager: UserSessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentProfileBinding.inflate(inflater,container,false)
        userSessionManager = UserSessionManager(requireContext())

        binding.profileLogoutButtonID.setOnClickListener {
            logoutUser()
        }
        return binding.root
    }

    private fun logoutUser() {
        // Kullanıcı verilerini sil (eğer varsa)
        // Kullanıcı oturum bilgilerini sil
        userSessionManager.logoutUser()
        // Giriş sayfasına yönlendir
        Navigation.loginPage(requireView(), R.id.action_profileFragment_to_loginFragment)

    }


}