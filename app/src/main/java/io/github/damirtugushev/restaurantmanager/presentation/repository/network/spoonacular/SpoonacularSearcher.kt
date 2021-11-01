package io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.model.SearchResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Client of the Spoonacular REST API defined by [SpoonacularApi].
 */
object SpoonacularSearcher {
    @JvmStatic
    private val LOG_TAG = SpoonacularSearcher::class.simpleName

    @JvmStatic
    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com/")
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()

    @JvmStatic
    private val spoonacularApi: SpoonacularApi =
        retrofit.create(SpoonacularApi::class.java) // MockSpoonacularApi

    @JvmStatic
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun searchComponentsSuspend(
        query: String,
        offset: Int, number: Int
    ): List<SearchResult> {
        return suspendCancellableCoroutine { continuation ->
            spoonacularApi.searchQuery(
                apiKey = "TOP-SECRET", query,
                offset, number
            ).enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>,
                ) {
                    if (response.isSuccessful) {
                        val searchResponse = requireNotNull(response.body())
                        val data = searchResponse.menuItems.map { SearchResult(it) }
                        continuation.resume(data)
                        return
                    }
                    val exception = IllegalStateException(response.errorBody()?.string())
                    Log.e(LOG_TAG, "Retrofit failure!", exception)
                    continuation.resumeWithException(exception)
                }

                override fun onFailure(call: Call<SearchResponse>, exception: Throwable) {
                    if (call.isCanceled) {
                        continuation.cancel()
                        return
                    }
                    Log.e(LOG_TAG, "Retrofit failure!", exception)
                    continuation.resumeWithException(exception)
                }
            })
        }
    }
}
