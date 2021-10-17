package io.github.damirtugushev.restaurantmanager.presentation.repository.network.the_meal_db.model

import io.github.damirtugushev.restaurantmanager.presentation.repository.network.the_meal_db.TheMealDbApi
import kotlinx.serialization.Serializable

/**
 * Response from the [TheMealDbApi.searchQuery].
 */
@Serializable
data class SearchResponse(
    val k: Int
)
