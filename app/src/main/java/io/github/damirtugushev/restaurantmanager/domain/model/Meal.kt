package io.github.damirtugushev.restaurantmanager.domain.model

/**
 * Base interface for all meals from a restaurant menu.
 */
interface Meal : Identifiable<String> {
    val name: String
    val description: String
}
