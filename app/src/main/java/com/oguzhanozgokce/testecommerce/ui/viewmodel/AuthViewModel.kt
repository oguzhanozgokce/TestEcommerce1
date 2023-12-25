package com.oguzhanozgokce.testecommerce.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.data.repo.UserRepository
import com.oguzhanozgokce.testecommerce.entitiy.User
import com.oguzhanozgokce.testecommerce.ui.login.RegistrationStatus
import com.oguzhanozgokce.testecommerce.ui.login.util.UserSessionManager
import com.oguzhanozgokce.testecommerce.ui.login.util.loginPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor (
    private val userRepository: UserRepository,
    private val userSessionManager: UserSessionManager
    ) : ViewModel() {
    private val _navigateToHome = MutableLiveData<Boolean?>()
    val navigateToHome: LiveData<Boolean?> = _navigateToHome

// Inside updateUserInfo


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
    fun updateUserInfo(email: String, phone: String, age: Int, address: String) {
        val userId = userSessionManager.getCurrentUserId()
        if (userId != -1) { // assuming -1 indicates no user
            viewModelScope.launch {
                val user = userRepository.getUserById(userId.toLong())
                user?.let {
                    val updatedUser = it.copy(email = email, phoneNumber = phone, age = age, address = address)
                    val updateResult = userRepository.updateUser(updatedUser)
                    // Ensure updateUser returns a Boolean indicating success or failure
                    _navigateToHome.value = updateResult
                } ?: run {
                    Log.e("AuthViewModel", "User not found with ID: $userId")
                    _navigateToHome.value = false // User not found
                }
            }
        } else {
            Log.e("AuthViewModel", "Invalid UserID: $userId")
            _navigateToHome.value = false // Invalid user ID
        }
    }
    fun doneNavigating() {
        _navigateToHome.value = null
    }




}