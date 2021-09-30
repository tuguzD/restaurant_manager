package io.github.damirtugushev.restaurantmanager.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.Repository
import io.github.damirtugushev.restaurantmanager.presentation.repository.RepositoryAccess
import io.github.damirtugushev.restaurantmanager.presentation.repository.RoomOrderRepository
import io.github.damirtugushev.restaurantmanager.presentation.repository.mock.MockOrder
import io.github.damirtugushev.restaurantmanager.presentation.repository.mock.MockOrderRepository
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import kotlinx.coroutines.launch

class OrderAddViewModel : ViewModel() {
    fun addOrder(tableNumber: Byte, guestsNumber: Byte) {
        val localRepository = RepositoryAccess.localRepository

        val order: Order = when (localRepository as Repository<out Order>) {
            is RoomOrderRepository -> OrderDto(tableNumber, guestsNumber)
            MockOrderRepository -> MockOrder(tableNumber, guestsNumber)
            else -> throw IllegalStateException("Unknown type of local repository!!!")
        }

        viewModelScope.launch {
            localRepository.addOrder(order)
        }
    }
}
