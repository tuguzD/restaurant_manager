package io.github.damirtugushev.restaurantmanager.presentation.view.delivery.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import io.github.damirtugushev.restaurantmanager.domain.model.Meal

object DeliveryDiffCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal) = oldItem.nanoId == newItem.nanoId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Meal, newItem: Meal) = oldItem == newItem
}
