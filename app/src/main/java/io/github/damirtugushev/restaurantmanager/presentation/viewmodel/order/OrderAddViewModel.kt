package io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import io.github.damirtugushev.restaurantmanager.presentation.repository.RepositoryAccess
import kotlinx.coroutines.launch

class OrderAddViewModel : ViewModel() {
    fun addOrder(tableNumber: Byte, guestsNumber: Byte) {
        val order = OrderData(
            nanoId = NanoIdUtils.randomNanoId(),
            tableNumber = tableNumber,
            guestsNumber = guestsNumber
        )

        viewModelScope.launch {
            RepositoryAccess.localRepository.add(order)
        }
    }
}
