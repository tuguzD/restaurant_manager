package io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order

import androidx.lifecycle.ViewModel
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import io.github.damirtugushev.restaurantmanager.presentation.repository.RepositoryAccess
import io.github.damirtugushev.restaurantmanager.presentation.view.order.OrderAddFragment

/**
 * [ViewModel] subclass for [OrderAddFragment].
 */
class OrderListViewModel : ViewModel() {
    val allData = RepositoryAccess.localOrderRepository.allData

    fun addOrder(tableNumber: Byte, guestsNumber: Byte) {
        val nanoId = NanoIdUtils.randomNanoId()

        val component = OrderData(nanoId, tableNumber, guestsNumber, null)
        RepositoryAccess.localOrderRepository.add(component)

//        val repository = RepositoryAccess.localOrderRepository
//        val order = when (repository as Repository<String, out Order>) {
//            MockOrderRepository -> OrderData(nanoId, tableNumber, guestsNumber, null)
//            else -> OrderDto(nanoId, tableNumber, guestsNumber, null)
//        }
//        repository.add(order)

    }

    fun updateOrder(item: OrderData) {
        RepositoryAccess.localOrderRepository.update(item)
//        val repository = RepositoryAccess.localOrderRepository
//        val order = when (repository as Repository<String, out Order>) {
//            is MockOrderRepository -> item
//            else -> OrderDto(item)
//        }
//        repository.update(order)
    }

    fun deleteOrder(order: Order) {
        RepositoryAccess.localOrderRepository.remove(order)
    }

    fun deleteAllOrders() {
        RepositoryAccess.localOrderRepository.clear()
    }
}
