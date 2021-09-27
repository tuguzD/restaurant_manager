package io.github.damirtugushev.restaurantmanager.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.damirtugushev.restaurantmanager.MainActivity
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderListViewModel : ViewModel() {
    fun getAllOrders(): LiveData<out List<Order>> {
        return MainActivity.repository.getAllOrders()
    }

    fun deleteOrder(order: OrderDto) {
        viewModelScope.launch(context = Dispatchers.IO) {
            MainActivity.repository.deleteOrder(order)
        }
    }

    fun deleteAllOrders() {
        viewModelScope.launch(context = Dispatchers.IO) {
            MainActivity.repository.deleteAllOrders()
        }
    }
}
