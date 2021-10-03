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
import io.github.damirtugushev.restaurantmanager.presentation.view.order.OrderAddFragment
import kotlinx.coroutines.launch

/**
 * [ViewModel] subclass for [OrderAddFragment].
 */
class OrderListViewModel : ViewModel() {
    val allOrders = RepositoryAccess.localRepository.allOrders

    fun addOrder(tableNumber: Byte, guestsNumber: Byte) {
        val nanoId = NanoIdUtils.randomNanoId()
        val repository = RepositoryAccess.localRepository
        val order = when (repository as Repository<out Order>) {
            MockOrderRepository -> OrderData(nanoId, tableNumber, guestsNumber, null)
            else -> OrderDto(nanoId, tableNumber, guestsNumber, null)
        }
        viewModelScope.launch {
            repository.add(order)
        }
    }

    fun updateOrder(item: OrderData) {
        val repository = RepositoryAccess.localRepository
        val order = when (repository as Repository<out Order>) {
            is MockOrderRepository -> item
            else -> OrderDto(item)
        }
        viewModelScope.launch {
            repository.update(order)
        }
    }

    fun deleteOrder(order: Order) {
        viewModelScope.launch {
            RepositoryAccess.localRepository.remove(order)
        }
    }

    fun deleteAllOrders() {
        viewModelScope.launch {
            RepositoryAccess.localRepository.clear()
        }
    }
}
