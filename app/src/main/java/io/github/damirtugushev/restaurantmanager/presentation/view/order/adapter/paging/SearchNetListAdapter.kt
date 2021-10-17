package io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import io.github.damirtugushev.restaurantmanager.databinding.ItemMealSearchBinding
import io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.SearchResult
import io.github.damirtugushev.restaurantmanager.presentation.view.DiffCallback
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order.OrderItemViewModel

/**
 * [PagingDataAdapter] subclass for searching results from the Spoonacular API.
 */
class SearchNetListAdapter(
    private val viewModel: OrderItemViewModel,
) : PagingDataAdapter<SearchResult, SearchResultViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ItemMealSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false,
        )
        return SearchResultViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}
