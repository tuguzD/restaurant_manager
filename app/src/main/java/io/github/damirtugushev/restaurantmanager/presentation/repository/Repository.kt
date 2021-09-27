package io.github.damirtugushev.restaurantmanager.presentation.repository

import androidx.lifecycle.LiveData
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto

/**
 * Base interface for all repositories.
 */
interface Repository {
    suspend fun addOrder(order: OrderDto)

    suspend fun deleteOrder(order: OrderDto)

    fun getAllOrders(): LiveData<out List<Order>>

    suspend fun deleteAllOrders()
}
