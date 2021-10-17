package io.github.damirtugushev.restaurantmanager.presentation.repository.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import io.github.damirtugushev.restaurantmanager.presentation.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Mock repository of [components][OrderData].
 */
object MockOrderRepository : Repository<OrderData> {
    private var list = List(20) { index ->
        OrderData(
            nanoId = NanoIdUtils.randomNanoId(),
            tableNumber = index.toByte(),
            guestsNumber = index.toByte(),
            documentUri = null,
        )
    }

    private val data = MutableLiveData(list)

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override val allOrders: LiveData<out List<OrderData>> get() = data

    override fun add(order: OrderData) {
        list = list + order
        coroutineScope.launch {
            data.value = list
        }
    }

    override fun update(order: OrderData) {
        val index = list.indexOfFirst { it.nanoId == order.nanoId }
        require(index > -1) { "No such item in repository: item is $order" }
        list = list.toMutableList().apply { set(index, order) }
        coroutineScope.launch {
            data.value = list
        }
    }

    override fun remove(order: OrderData) {
        list = list - order
        coroutineScope.launch {
            data.value = list
        }
    }

    override fun clear() {
        list = listOf()
        coroutineScope.launch {
            data.value = list
        }
    }
}
