package io.github.damirtugushev.restaurantmanager.domain.model

/**
 * Represent objects which can be identified by ID.
 */
interface Identifiable<out T> {
    val nanoId: T
}
