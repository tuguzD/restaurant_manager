package io.github.damirtugushev.restaurantmanager.presentation.model

import android.os.Parcelable
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderData(
    override val nanoId: String,
    override val tableNumber: Byte,
    override val guestsNumber: Byte,
) : Order, Parcelable {
    constructor(o: Order) : this(o.nanoId, o.tableNumber, o.guestsNumber)
}
