package com.oguzhanozgokce.testecommerce.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.testecommerce.data.repo.UserRepository
import com.oguzhanozgokce.testecommerce.ui.login.RegistrationStatus
import com.oguzhanozgokce.testecommerce.ui.login.util.UserSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor (
    private val userRepository: UserRepository,
    private val userSessionManager: UserSessionManager
    ) : ViewModel() {

    private val _registrationStatus = MutableLiveData<RegistrationStatus>()
    val registrationStatus: LiveData<RegistrationStatus> = _registrationStatus

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> = _loginStatus

    // Kullanıcı kaydı için fonksiyon
    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            userRepository.registerUser(username, password)
            userSessionManager.saveUserLogin(username) // Başarılı kayıt durumunu kaydet
            _registrationStatus.value = RegistrationStatus.SUCCESS
        }
    }

    // Kullanıcı girişi için fonksiyon
    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val isLoginSuccessful = userRepository.loginUser(username, password)
            if (isLoginSuccessful) {
                userSessionManager.saveUserLogin(username) // Başarılı giriş durumunu kaydet
            }
            _loginStatus.value = isLoginSuccessful
        }
    }

}