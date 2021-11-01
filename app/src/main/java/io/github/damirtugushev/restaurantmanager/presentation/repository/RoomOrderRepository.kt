package io.github.damirtugushev.restaurantmanager.presentation.repository

import androidx.lifecycle.LiveData
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.RoomDatabase
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Room repository of [orders][Order].
 *
 * @see Order
 */
internal class RoomOrderRepository(private val roomDatabase: RoomDatabase) :
    Repository<String, Order> {

    private val orderDao get() = roomDatabase.orderDao

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override val allData = orderDao.getAll()

    override fun findById(nanoId: String): LiveData<out Order> = orderDao.findById(nanoId)

    override fun add(item: Order) {
        val component = when (item) {
            is OrderData -> OrderDto(item)
            is OrderDto -> item
            else -> throw IllegalStateException(
                "Data loss: item must be convertible to ${OrderDto::class.qualifiedName}"
            )
        }
        coroutineScope.launch {
            orderDao.insert(component)
        }
    }

    override fun update(item: Order) {
        val component = when (item) {
            is OrderData -> OrderDto(item)
            is OrderDto -> item
            else -> throw IllegalStateException(
                "Data loss: item must be convertible to ${OrderDto::class.qualifiedName}"
            )
        }
        coroutineScope.launch {
            orderDao.update(component)
        }
    }

    override fun remove(item: Order) {
        val component = when (item) {
            is OrderData -> OrderDto(item)
            is OrderDto -> item
            else -> throw IllegalStateException(
                "Data loss: item must be convertible to ${OrderDto::class.qualifiedName}"
            )
        }
        coroutineScope.launch {
            orderDao.delete(component)
        }
    }

    override fun clear() {
        coroutineScope.launch { orderDao.deleteAll() }
    }
}
