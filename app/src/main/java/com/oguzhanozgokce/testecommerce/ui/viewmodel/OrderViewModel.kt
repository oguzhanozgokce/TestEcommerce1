package com.oguzhanozgokce.testecommerce.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.testecommerce.data.repo.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val orderRepository: OrderRepository) : ViewModel() {
    // LiveData to observe order placement status
    private val _orderStatus = MutableLiveData<Boolean>()
    val orderStatus: LiveData<Boolean> = _orderStatus


    // Function to place an order
    fun placeOrder(userId: Int, productId: Int) {
        viewModelScope.launch {
            if (userId > 0 && productId > 0) {
                try {
                    orderRepository.placeOrder(userId, productId)
                    _orderStatus.value = true
                } catch (e: Exception) {
                    _orderStatus.value = false
                    Log.e("OrderViewModel", "Error placing order: ${e.localizedMessage}")
                }
            } else {
                _orderStatus.value = false
                Log.e("OrderViewModel", "Invalid userId or productId: userId=$userId, productId=$productId")
            }
        }
    }


}