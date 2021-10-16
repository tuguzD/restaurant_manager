package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto

/**
 * Data access object (Dao) for [Order] class.
 */
@Dao
interface OrderDao {
    @Insert
    suspend fun insert(order: OrderDto)

    @Update
    suspend fun update(order: OrderDto)

    @Delete
    suspend fun delete(order: OrderDto)

    @Query("SELECT * FROM `order`")
    fun getAll(): LiveData<List<OrderDto>>

    @Query("DELETE FROM `order`")
    suspend fun deleteAll()
}
