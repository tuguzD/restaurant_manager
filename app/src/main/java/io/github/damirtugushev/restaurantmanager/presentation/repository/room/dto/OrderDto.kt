package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.damirtugushev.restaurantmanager.domain.model.Order

/**
 * Data transfer object (Dto) for [Order].
 */
@Entity(tableName = "order")
data class OrderDto(
    override val nanoId: String,
    override val tableNumber: Byte,
    override val guestsNumber: Byte,
    @PrimaryKey(autoGenerate = true) var oid: Long,
) : Order {
    constructor(o: Order) : this(o.nanoId, o.tableNumber, o.guestsNumber, oid = 0L)
}
