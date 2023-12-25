package com.oguzhanozgokce.testecommerce.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.testecommerce.data.repo.UserRepository
import com.oguzhanozgokce.testecommerce.entitiy.User
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
    fun registerUser(name: String, surname: String, username: String, password: String) {
        viewModelScope.launch {
            _registrationStatus.value = RegistrationStatus.IN_PROGRESS
            val userId = userRepository.registerUser(name, surname, username, password)
            if (userId > 0) {
                val user = userRepository.getUserById(userId)  // Assuming you have a method to retrieve User by ID
                user?.let {
                    userSessionManager.saveUserLogin(it)
                }
                _registrationStatus.value = RegistrationStatus.SUCCESS
            } else {
                _registrationStatus.value = RegistrationStatus.FAILURE
            }
        }
    }


    // Kullanıcı girişi için fonksiyon
    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.loginUser(username, password)
            if (user != null) {
                userSessionManager.saveUserLogin(user)  // Save user details
                _loginStatus.value = true  // Set login status to true
            } else {
                _loginStatus.value = false  // Set login status to false
            }
        }
    }

}