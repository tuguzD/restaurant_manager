package io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular

import io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.model.SearchResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MockSpoonacularApi : SpoonacularApi {

    override fun searchQuery(
        apiKey: String, query: String,
//        addMenuItemInformation: Boolean,
        offset: Int, number: Int
    ) = object: Call<SearchResponse> {

        override fun execute(): Response<SearchResponse> {
            val searchResponse = json.decodeFromString<SearchResponse>(response)
            return Response.success(searchResponse)
        }

        override fun enqueue(callback: Callback<SearchResponse>) {
            val call = this
            CoroutineScope(Dispatchers.IO).launch {
                val searchResponse = execute()
                callback.onResponse(call, searchResponse)
            }
        }

        override fun cancel() = Unit
        override fun clone() = this
        override fun isExecuted() = false
        override fun isCanceled() = false

        override fun timeout() = Timeout.NONE
        override fun request() = Request.Builder().build()
    }
}

private const val response = "{\n" +
        "    \"menuItems\": [\n" +
        "        {\n" +
        "            \"id\": 419357,\n" +
        "            \"title\": \"Burger Sliders\",\n" +
        "            \"restaurantChain\": \"Hooters\",\n" +
        "            \"image\": \"https://images.spoonacular.com/file/wximages/419357-312x231.png\",\n" +
        "            \"imageType\": \"png\",\n" +
        "            \"servings\": {\n" +
        "                \"number\": 1,\n" +
        "                \"size\": 2,\n" +
        "                \"unit\": \"oz\"\n" +
        "            }\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 424571,\n" +
        "            \"title\": \"Bacon King Burger\",\n" +
        "            \"restaurantChain\": \"Burger King\",\n" +
        "            \"image\": \"https://images.spoonacular.com/file/wximages/424571-312x231.png\",\n" +
        "            \"imageType\": \"png\",\n" +
        "            \"servings\": {\n" +
        "                \"number\": 1,\n" +
        "                \"size\": 2,\n" +
        "                \"unit\": \"oz\"\n" +
        "            }\n" +
        "        }\n" +
        "    ],\n" +
        "    \"totalMenuItems\": 6749,\n" +
        "    \"type\": \"menuItem\",\n" +
        "    \"offset\": 0,\n" +
        "    \"number\": 2\n" +
        "}"
