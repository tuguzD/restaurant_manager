package io.github.damirtugushev.restaurantmanager.presentation.repository.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.damirtugushev.restaurantmanager.presentation.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Mock repository of [components][MockOrder].
 */
object MockOrderRepository : Repository<MockOrder> {
    private var list = List(20) { index ->
        MockOrder(
            tableNumber = index.toByte(),
            guestsNumber = index.toByte(),
        )
    }

    private val data = MutableLiveData(list)

    override val defaultDispatcher = Dispatchers.Main

    override fun getAllOrders(): LiveData<out List<MockOrder>> = data

    override suspend fun addOrder(order: MockOrder) =
        withContext(defaultDispatcher) {
            list = list + order
            data.value = list
        }

    override suspend fun deleteOrder(order: MockOrder) =
        withContext(defaultDispatcher) {
            list = list - order
            data.value = list
        }

    override suspend fun deleteAllOrders() =
        withContext(defaultDispatcher) {
            list = listOf()
            data.value = list
        }
}
