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

    override fun getAllOrders(): LiveData<List<OrderDto>> = ordersDao.getAllOrders()

    override suspend fun addOrder(order: OrderDto) =
        withContext(defaultDispatcher) { ordersDao.addOrder(order) }

    override suspend fun deleteOrder(order: OrderDto) =
        withContext(defaultDispatcher) { ordersDao.deleteOrder(order) }

    override suspend fun deleteAllOrders() =
        withContext(defaultDispatcher) { ordersDao.deleteAllOrders() }

}
