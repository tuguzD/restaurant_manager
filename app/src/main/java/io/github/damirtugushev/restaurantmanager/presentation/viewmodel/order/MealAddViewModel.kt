package io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter.paging.SearchNetPagingSource

class MealAddViewModel : ViewModel() {
    fun searchMeals(query: String, pageSize: Int) = Pager(
        PagingConfig(pageSize, enablePlaceholders = false),
        pagingSourceFactory = { SearchNetPagingSource(query, pageSize) }
    ).liveData.cachedIn(viewModelScope)
}
