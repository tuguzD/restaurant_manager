package io.github.damirtugushev.restaurantmanager.presentation.view.delivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.damirtugushev.restaurantmanager.databinding.ItemDeliveryBinding
import io.github.damirtugushev.restaurantmanager.domain.model.Meal

/**
 * [RecyclerView.Adapter] that can display a [Meal].
 */
class BuildListAdapter : ListAdapter<Meal, DeliveryViewHolder>(DeliveryDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val binding = ItemDeliveryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return DeliveryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        val build = currentList[position]
        holder.bind(build)
    }

    fun add(index: Int, meal: Meal) {
        val newList = currentList.toMutableList().apply {
            add(index, meal)
        }
        submitList(newList)
    }

    fun removeAt(position: Int): Meal {
        val meal = currentList[position]
        submitList(currentList - meal)
        return meal
    }
}
