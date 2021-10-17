package io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.SearchResult
import io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.SpoonacularSearcher

class SearchNetPagingSource(
    private val query: String,
    private val pageSize: Int,
) : PagingSource<Int, SearchResult>() {

    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
        val position = params.key ?: 0
        val offset = if (params.key != null) (position * pageSize) else 0
        return try {
            val data = SpoonacularSearcher.searchComponentsSuspend(query, offset, pageSize)
            val nextKey = if (data.isEmpty()) null else position + 1
            LoadResult.Page(data, prevKey = null, nextKey)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
