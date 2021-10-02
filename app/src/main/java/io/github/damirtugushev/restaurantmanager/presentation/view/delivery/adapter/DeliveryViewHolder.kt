package io.github.damirtugushev.restaurantmanager.presentation.view.delivery.adapter

import androidx.recyclerview.widget.RecyclerView
import io.github.damirtugushev.restaurantmanager.databinding.ItemDeliveryBinding
import io.github.damirtugushev.restaurantmanager.domain.model.Meal

class DeliveryViewHolder(private val binding: ItemDeliveryBinding) :
    RecyclerView.ViewHolder(binding.root) {

        private lateinit var _meal: Meal
        val meal get() = _meal

        fun bind(meal: Meal) {
            // todo: not implemented yet
        }
}
