package io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter.paging

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.squareup.picasso.Picasso
import io.github.damirtugushev.restaurantmanager.databinding.ItemMealSearchBinding
import io.github.damirtugushev.restaurantmanager.presentation.model.MealData
import io.github.damirtugushev.restaurantmanager.presentation.repository.network.spoonacular.SearchResult
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order.OrderItemViewModel

class SearchResultViewHolder(
    private val binding: ItemMealSearchBinding,
    private val viewModel: OrderItemViewModel,
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var _searchResult: SearchResult
    private val searchResult get() = _searchResult

    init {
        binding.saveOrderList.setOnClickListener {
            val mealData = MealData(
                NanoIdUtils.randomNanoId(), searchResult.name,
                searchResult.description, null,
                searchResult.imageUrl
            )
            viewModel.addMeal(mealData)
            binding.saveOrderList.visibility = View.GONE
        }
    }

    fun bind(searchResult: SearchResult) {
        _searchResult = searchResult
        binding.run {
            name.text = searchResult.name
            description.text = searchResult.description
            Picasso.get()
                .load(searchResult.imageUrl)
                .into(imageView)
            imageView.visibility = View.VISIBLE
        }
    }
}
