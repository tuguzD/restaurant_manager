package io.github.damirtugushev.restaurantmanager.presentation.repository.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import io.github.damirtugushev.restaurantmanager.presentation.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Mock repository of [components][OrderData].
 */
object MockOrderRepository : Repository<OrderData> {
    private var list = List(20) { index ->
        OrderData(
            nanoId = NanoIdUtils.randomNanoId(),
            tableNumber = index.toByte(),
            guestsNumber = index.toByte(),
        )
    }

    private val data = MutableLiveData(list)

    override val defaultDispatcher = Dispatchers.Main

    override val allOrders: LiveData<out List<OrderData>> get() = data

    override suspend fun add(order: Order) {
        @Suppress("NAME_SHADOWING")
        val order = when (order) {
            is OrderData -> order
            else -> OrderData(order)
        }

        list = list + order
        withContext(defaultDispatcher) {
            data.value = list
        }
    }

    override suspend fun remove(order: OrderData) {
        list = list - order
        withContext(defaultDispatcher) {
            data.value = list
        }
    }

    override suspend fun clear() {
        list = listOf()
        withContext(defaultDispatcher) {
            data.value = list
        }
    }
}
