package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.damirtugushev.restaurantmanager.domain.model.Order

/**
 * Data transfer object (Dto) for [Order].
 */

@Entity(tableName = "order")
data class OrderDto(
    @PrimaryKey(autoGenerate = true) val oid: Int,
    override val tableNumber: UByte,
    override val guestsNumber: UByte,
) : Order
