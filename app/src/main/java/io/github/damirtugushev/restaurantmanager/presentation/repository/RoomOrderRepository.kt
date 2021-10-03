package io.github.damirtugushev.restaurantmanager.presentation.repository

import android.app.Application
import androidx.lifecycle.LiveData
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Safe wrapper around Room database.
 *
 * @see RoomOrderDatabase
 */
class RoomOrderRepository internal constructor(application: Application) : Repository<OrderDto> {

    private val roomDatabase = RoomOrderDatabase.getInstance(application)
    private val ordersDao get() = roomDatabase.ordersDao

    override val defaultDispatcher = Dispatchers.IO

    override val allOrders: LiveData<List<OrderDto>> = ordersDao.getAll()

    override suspend fun add(order: OrderDto) =
        withContext(defaultDispatcher) { ordersDao.insert(order) }

    override suspend fun update(order: OrderDto) =
        withContext(defaultDispatcher) { ordersDao.update(order) }

    override suspend fun remove(order: OrderDto) =
        withContext(defaultDispatcher) { ordersDao.delete(order) }

    override suspend fun clear() =
        withContext(defaultDispatcher) { ordersDao.deleteAll() }

}
