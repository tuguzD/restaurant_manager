package io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular

import io.github.damirtugushev.restaurantmanager.domain.model.Identifiable
import io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.model.MealResult
import io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.model.SearchResponse

/**
 * Represents the [result][SearchResponse] of searching query
 * by [SpoonacularSearcher.searchComponentsSuspend].
 */
data class SearchResult(private val mealResult: MealResult) : Identifiable<String> {
    override val nanoId get() = mealResult.id.toString()
    val name get() = mealResult.title
    val description get() = mealResult.restaurantChain
    val imageUrl get() = mealResult.image
}
