package io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.RepositoryAccess
import io.github.damirtugushev.restaurantmanager.presentation.view.order.OrderAddFragment
import kotlinx.coroutines.launch

/**
 * [ViewModel] subclass for [OrderAddFragment].
 */
class OrderListViewModel : ViewModel() {
    val allOrders = RepositoryAccess.localRepository.allOrders

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
