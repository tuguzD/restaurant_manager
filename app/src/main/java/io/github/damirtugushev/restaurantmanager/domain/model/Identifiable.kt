package io.github.damirtugushev.restaurantmanager.domain.model

/**
 * Represent objects which can be identified by ID.
 */
interface Identifiable<T : Comparable<T>> {
    val nanoId: T
}
