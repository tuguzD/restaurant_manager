package io.github.damirtugushev.restaurantmanager.domain.model

/**
 * Base class for all meals from a restaurant menu.
 */

open class Meal(
    open val name: String,
    open val description: String,
)
