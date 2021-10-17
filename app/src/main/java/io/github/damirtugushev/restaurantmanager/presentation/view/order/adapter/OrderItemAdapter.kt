package io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.damirtugushev.restaurantmanager.databinding.ItemMealBinding
import io.github.damirtugushev.restaurantmanager.domain.model.Meal
import io.github.damirtugushev.restaurantmanager.presentation.model.MealData
import io.github.damirtugushev.restaurantmanager.presentation.view.DiffCallback

/**
 * [RecyclerView.Adapter] that can display a [Meal].
 */
class OrderItemAdapter : ListAdapter<Meal,MealViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false,
        )
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = when (val meal = currentList[position]) {
            is MealData -> meal
            else -> MealData(meal)
        }
        holder.bind(meal)
    }
}
