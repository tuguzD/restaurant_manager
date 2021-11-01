package io.github.damirtugushev.restaurantmanager.presentation.repository.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import io.github.damirtugushev.restaurantmanager.presentation.repository.Repository
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Mock repository of [components][Order].
 */
object MockOrderRepository : Repository<String, Order> {
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

    override val allData: LiveData<out List<Order>> get() = data

    override fun findById(nanoId: String): LiveData<out Order> =
        MutableLiveData<OrderData>().apply {
            data.observeForever { components ->
                value = components.find { it.nanoId == nanoId }
            }
        }

    override fun add(item: Order) {
        val order = when (item) {
            is OrderData -> item
            is OrderDto -> OrderData(item)
            else -> throw IllegalStateException(
                "Data loss: item must be convertible to ${OrderData::class.qualifiedName}"
            )
        }
        list = list + order
        coroutineScope.launch {
            data.value = list
        }
    }

    override fun update(item: Order) {
        val order = when (item) {
            is OrderData -> item
            is OrderDto -> OrderData(item)
            else -> throw IllegalStateException(
                "Data loss: item must be convertible to ${OrderData::class.qualifiedName}"
            )
        }
        val index = list.indexOfFirst { it.nanoId == order.nanoId }
        require(index > -1) { "No such item in repository: item is $order" }

        list = list.toMutableList().apply { set(index, order) }
        coroutineScope.launch {
            data.value = list
        }
    }

    override fun remove(item: Order) {
        val order = when (item) {
            is OrderData -> item
            is OrderDto -> OrderData(item)
            else -> throw IllegalStateException(
                "Data loss: item must be convertible to ${OrderData::class.qualifiedName}"
            )
        }
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
