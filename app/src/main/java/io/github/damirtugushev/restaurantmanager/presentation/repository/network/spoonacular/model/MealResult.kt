package io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.model

import kotlinx.serialization.Serializable

/**
 * Data about the part from the [SearchResponse.menuItems].
 */
@Serializable
data class MealResult(
    val id: Int,
    val title: String,
    val restaurantChain: String,
    val image: String,
)
