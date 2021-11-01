package io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import io.github.damirtugushev.restaurantmanager.presentation.repository.Repository
import io.github.damirtugushev.restaurantmanager.presentation.repository.RepositoryAccess
import io.github.damirtugushev.restaurantmanager.presentation.repository.mock.MockOrderRepository
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import kotlinx.coroutines.launch

class OrderAddViewModel : ViewModel() {
    fun addOrder(tableNumber: Byte, guestsNumber: Byte) {
        val nanoId = NanoIdUtils.randomNanoId()
        val repository = RepositoryAccess.localOrderRepository
        val order = when (repository as Repository<String, out Order>) {
            is MockOrderRepository -> OrderData(nanoId, tableNumber, guestsNumber, null)
            else -> OrderDto(nanoId, tableNumber, guestsNumber, null)
        }

        viewModelScope.launch {
            RepositoryAccess.localOrderRepository.add(order)
        }
    }
}
