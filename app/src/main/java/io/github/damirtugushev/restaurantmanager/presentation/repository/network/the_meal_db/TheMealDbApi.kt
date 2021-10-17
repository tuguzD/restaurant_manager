package io.github.damirtugushev.restaurantmanager.presentation.repository.network.the_meal_db

import io.github.damirtugushev.restaurantmanager.presentation.repository.network.the_meal_db.model.SearchResponse
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Definition of [The Meal DB API](https://www.themealdb.com/api.php).
 */
interface TheMealDbApi {
    /**
     * Search for the components by [query].
     */
    @GET("search.php?")
    fun searchQuery(
        @Query("s") query: String,
    ): Call<SearchResponse>
}

internal val json = Json { ignoreUnknownKeys = true }
