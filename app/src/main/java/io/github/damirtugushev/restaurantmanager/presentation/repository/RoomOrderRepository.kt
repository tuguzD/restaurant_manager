package io.github.damirtugushev.restaurantmanager.presentation.repository

import android.app.Application
import androidx.lifecycle.LiveData
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Safe wrapper around Room database.
 *
 * @see RoomOrderDatabase
 */
class RoomOrderRepository internal constructor(application: Application) : Repository<OrderDto> {

    private val roomDatabase = RoomOrderDatabase.getInstance(application)
    private val ordersDao get() = roomDatabase.ordersDao

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override val allOrders: LiveData<List<OrderDto>> = ordersDao.getAll()

    override fun add(order: OrderDto) {
        coroutineScope.launch { ordersDao.insert(order) }
    }

    override fun update(order: OrderDto) {
        coroutineScope.launch { ordersDao.update(order) }
    }

    override fun remove(order: OrderDto) {
        coroutineScope.launch { ordersDao.delete(order) }
    }

    override fun clear() {
        coroutineScope.launch { ordersDao.deleteAll() }
    }
}
