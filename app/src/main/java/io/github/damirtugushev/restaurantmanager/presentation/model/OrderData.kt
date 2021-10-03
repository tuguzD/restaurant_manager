package io.github.damirtugushev.restaurantmanager.presentation.model

import android.os.Parcelable
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderData(
    override val nanoId: String,
    override val tableNumber: Byte,
    override val guestsNumber: Byte,
    val documentUri: String?
) : Order, Parcelable {
    constructor(o: Order) : this(o.nanoId, o.tableNumber, o.guestsNumber, null)
    constructor(o: OrderDto) : this(o.nanoId, o.tableNumber, o.guestsNumber, o.documentUri)
}
