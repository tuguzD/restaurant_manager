package io.github.damirtugushev.restaurantmanager.presentation.repository

import android.app.Application
import androidx.lifecycle.LiveData
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto

/**
 * Safe wrapper around Room database.
 *
 * @see OrderRoomDatabase
 */
class OrderRepository(application: Application) : Repository {
    private val roomDatabase = OrderRoomDatabase.getInstance(application)
    private val orderDao = roomDatabase.ordersDao

    override suspend fun addOrder(order: OrderDto) {
        orderDao.addOrder(order)
    }

    override suspend fun deleteOrder(order: OrderDto) {
        orderDao.deleteOrder(order)
    }

    override fun getAllOrders(): LiveData<out List<Order>> {
        return orderDao.getAllOrders()
    }

    override suspend fun deleteAllOrders() {
        orderDao.deleteAllOrders()
    }

}
