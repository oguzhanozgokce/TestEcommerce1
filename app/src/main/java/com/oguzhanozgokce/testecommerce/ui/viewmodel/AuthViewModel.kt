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

    private val _registrationStatus = MutableLiveData<RegistrationStatus>()
    val registrationStatus: LiveData<RegistrationStatus> = _registrationStatus

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> = _loginStatus

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun loadUserInfo(userId: Int) {
        viewModelScope.launch {
            _user.value = userRepository.getUserById(userId.toLong())
        }
    }

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
        if (userId != -1) {
            viewModelScope.launch {
                val updateResult = userRepository.updateUserInfo(userId.toLong(), email, phone, age, address)
                _navigateToHome.value = updateResult // true if successful, false otherwise
            }
        } else {
            Log.e("AuthViewModel", "Invalid UserID: $userId")
            _navigateToHome.value = false // Invalid user ID
        }
    }
    fun updateUserDetails(newName: String, newUsername: String, newPassword: String, newEmail: String, newPhone: String, newAge: Int, newAddress: String) {
        Log.e("UpdateUser", "Name: $newName, Username: $newUsername, Password: $newPassword, Email: $newEmail, Phone: $newPhone, Age: $newAge, Address: $newAddress")
        val userId = userSessionManager.getCurrentUserId()
        if (userId != -1) {
            viewModelScope.launch {
                val currentUser = userRepository.getUserById(userId.toLong())
                currentUser?.let {
                    // Create an updated user object with the new information
                    val updatedUser = it.copy(
                        name = newName,
                        username = newUsername,
                        password = newPassword,
                        email = newEmail,
                        phoneNumber = newPhone,
                        age = newAge,
                        address = newAddress
                    )
                    val updateResult = userRepository.updateUser(updatedUser)
                    _navigateToHome.value = updateResult // true if successful, false otherwise
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