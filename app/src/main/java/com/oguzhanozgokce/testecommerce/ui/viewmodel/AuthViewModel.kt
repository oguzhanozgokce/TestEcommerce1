package com.oguzhanozgokce.testecommerce.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.testecommerce.data.repo.UserRepository
import com.oguzhanozgokce.testecommerce.ui.login.RegistrationStatus
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
@HiltViewModel
class AuthViewModel @Inject constructor (private val userRepository: UserRepository) : ViewModel() {

    private val _registrationStatus = MutableLiveData<RegistrationStatus>()
    val registrationStatus: LiveData<RegistrationStatus> = _registrationStatus

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> = _loginStatus

    // Kullanıcı kaydı için fonksiyon
    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            userRepository.registerUser(username, password)
            _registrationStatus.value = RegistrationStatus.SUCCESS
        }
    }
    // Kullanıcı girişi için fonksiyon
    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val isLoginSuccessful = userRepository.loginUser(username, password)
            _loginStatus.value = isLoginSuccessful
        }
    }
}