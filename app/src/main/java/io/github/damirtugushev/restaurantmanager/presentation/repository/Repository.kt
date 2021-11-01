package io.github.damirtugushev.restaurantmanager.presentation.repository

import androidx.lifecycle.LiveData
import io.github.damirtugushev.restaurantmanager.domain.model.Identifiable

/**
 * Base interface for all repositories which contains data of type [T].
 */
interface Repository<I : Any, T : Identifiable<I>> {
    val allData: LiveData<out List<T>>

    fun findById(nanoId: I): LiveData<out T>

    fun add(item: T)

    fun update(item: T)

    fun remove(item: T)

    fun clear()
}

//fun <C : Order> Repository<C>.findById(nanoId: String, owner: LifecycleOwner) : LiveData<Order> =
//    MutableLiveData<Order>().apply {
//        allOrders.observe(owner) { orders ->
//            value = orders.find { it.nanoId == nanoId }
//        }
//    }
