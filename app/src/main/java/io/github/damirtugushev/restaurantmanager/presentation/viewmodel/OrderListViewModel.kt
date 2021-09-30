package io.github.damirtugushev.restaurantmanager.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.RepositoryAccess
import io.github.damirtugushev.restaurantmanager.presentation.view.OrderAddFragment
import kotlinx.coroutines.launch

/**
 * [ViewModel] subclass for [OrderAddFragment].
 */
class OrderListViewModel : ViewModel() {
    fun getAllOrders(): LiveData<out List<Order>> {
        return RepositoryAccess.localRepository.getAllOrders()
    }

    fun deleteOrder(order: Order) {
        viewModelScope.launch {
            RepositoryAccess.localRepository.deleteOrder(order)
        }
    }

    fun deleteAllOrders() {
        viewModelScope.launch {
            RepositoryAccess.localRepository.deleteAllOrders()
        }
    }
}
