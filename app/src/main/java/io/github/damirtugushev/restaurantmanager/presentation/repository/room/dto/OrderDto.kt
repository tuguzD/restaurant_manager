package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData

/**
 * Data transfer object (Dto) for [Order].
 */
@Entity(tableName = "order")
data class OrderDto(
    @PrimaryKey override val nanoId: String,
    override val tableNumber: Byte,
    override val guestsNumber: Byte,
    @ColumnInfo(name = "document_uri") val documentUri: String?,
) : Order {
    constructor(o: Order) : this(o.nanoId, o.tableNumber, o.guestsNumber, null)
    constructor(o: OrderData) : this(o.nanoId, o.tableNumber, o.guestsNumber, o.documentUri)
}
