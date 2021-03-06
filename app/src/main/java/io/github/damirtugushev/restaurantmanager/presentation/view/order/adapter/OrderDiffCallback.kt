package io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import io.github.damirtugushev.restaurantmanager.domain.model.Order

object OrderDiffCallback : DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(oldItem: Order, newItem: Order) = oldItem.nanoId == newItem.nanoId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Order, newItem: Order) = oldItem == newItem
}
