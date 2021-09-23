package io.github.damirtugushev.restaurantmanager.domain.model

/**
 * Base class for all orders made in the restaurant.
 */

open class Order(
    open val tableNumber: UByte,
    open val guestsNumber: UByte,
)
