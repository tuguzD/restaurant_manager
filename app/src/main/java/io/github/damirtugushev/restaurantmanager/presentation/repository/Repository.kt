package io.github.damirtugushev.restaurantmanager.presentation.repository

import androidx.lifecycle.LiveData
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Base interface for all repositories.
 */
interface Repository<O : Order> {
    val defaultDispatcher: CoroutineDispatcher

    fun getAllOrders(): LiveData<out List<O>>

    suspend fun addOrder(order: O)

    suspend fun deleteOrder(order: O)

    suspend fun deleteAllOrders()
}
