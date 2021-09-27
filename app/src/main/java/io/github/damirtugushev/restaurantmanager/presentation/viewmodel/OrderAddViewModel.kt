package io.github.damirtugushev.restaurantmanager.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.damirtugushev.restaurantmanager.MainActivity
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderAddViewModel : ViewModel() {
    fun addOrder(tableNumber: UByte, guestsNumber: UByte) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val order = OrderDto(
                tableNumber = tableNumber,
                guestsNumber = guestsNumber,
            )
            MainActivity.repository.addOrder(order)
        }
    }
}
