package io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular

import io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.model.SearchResponse
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Definition of the [Spoonacular API](https://spoonacular.com/food-api/).
 */
interface SpoonacularApi {
    /**
     * Search for the components by [query].
     * Use [offset] and [number] arguments for paging.
     */
    @GET("food/menuItems/search?")
    @Headers("Content-Type: application/json")
    fun searchQuery(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
//        @Query("addMenuItemInformation") addMenuItemInformation: Boolean,
        @Query("offset") offset: Int,
        @Query("number") number: Int,
    ): Call<SearchResponse>
}

internal val json = Json { ignoreUnknownKeys = true }
