package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto

/**
 * Data access object (Dao) for [Order] class.
 */
@Dao
interface OrderDao : IDao<OrderDto> {
    @Query("SELECT * FROM `order` WHERE nanoId = :nanoId")
    fun findById(nanoId: String): LiveData<OrderDto>

    @Query("SELECT * FROM `order`")
    fun getAll(): LiveData<List<OrderDto>>

    @Query("DELETE FROM `order`")
    suspend fun deleteAll()
}
