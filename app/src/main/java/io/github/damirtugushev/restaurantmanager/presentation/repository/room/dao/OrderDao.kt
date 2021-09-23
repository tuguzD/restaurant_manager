package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto

/**
 * Data access object (Dao) for [Order] class.
 */

@Dao
interface OrderDao {
    @Insert
    fun addOrder(order: OrderDto)

    @Delete
    fun deleteOrder(order: OrderDto)

    @Query("SELECT * FROM order")
    fun getAllOrders(): LiveData<List<OrderDto>>

    @Query("DELETE FROM order")
    fun deleteAllOrders()
}
