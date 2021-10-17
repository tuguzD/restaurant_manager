package io.github.damirtugushev.restaurantmanager.presentation.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.damirtugushev.restaurantmanager.domain.model.Order

/**
 * Base interface for all repositories.
 */
interface Repository<O : Order> {
    val allOrders: LiveData<out List<O>>

    fun add(order: O)
    fun update(order: O)
    fun remove(order: O)
    fun clear()
}

fun <C : Order> Repository<C>.findById(nanoId: String, owner: LifecycleOwner) : LiveData<Order> =
    MutableLiveData<Order>().apply {
        allOrders.observe(owner) { components ->
            value = components.find { it.nanoId == nanoId }
        }
    }
