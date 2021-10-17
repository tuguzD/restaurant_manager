package io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.model

import io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.SpoonacularApi
import kotlinx.serialization.Serializable

/**
 * Response from the [SpoonacularApi.searchQuery].
 */
@Serializable
data class SearchResponse(
    val menuItems: List<MealResult>
)
