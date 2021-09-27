package io.github.damirtugushev.restaurantmanager.domain.model

/**
 * Base interface for all meals from a restaurant menu.
 */
interface Meal {
    val name: String
    val description: String
}
