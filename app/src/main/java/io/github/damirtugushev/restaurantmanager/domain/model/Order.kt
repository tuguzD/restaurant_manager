package io.github.damirtugushev.restaurantmanager.domain.model

/**
 * Base interface for all orders made in the restaurant.
 */
interface Order : Identifiable<String> {
    val tableNumber: Byte
    val guestsNumber: Byte
}
