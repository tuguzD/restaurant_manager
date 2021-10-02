package io.github.damirtugushev.restaurantmanager.presentation.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Base interface for all repositories.
 */
interface Repository<O : Order> {
    val defaultDispatcher: CoroutineDispatcher

    val allOrders: LiveData<out List<O>>

    suspend fun add(order: Order)

    suspend fun remove(order: O)

    suspend fun clear()
}

fun <C : Order> Repository<C>.findById(nanoId: String, owner: LifecycleOwner) : LiveData<Order> =
    MutableLiveData<Order>().apply {
        allOrders.observe(owner) { components ->
            value = components.find { it.nanoId == nanoId }
        }
    }
