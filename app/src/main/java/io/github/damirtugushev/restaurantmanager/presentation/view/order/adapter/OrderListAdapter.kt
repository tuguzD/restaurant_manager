package io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.damirtugushev.restaurantmanager.databinding.ItemOrderBinding
import io.github.damirtugushev.restaurantmanager.domain.model.Order

/**
 * [RecyclerView.Adapter] that can display an [Order].
 */
class OrderListAdapter : ListAdapter<Order, OrderViewHolder>(OrderDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val component = currentList[position]
        holder.bind(component)
    }

    fun add(index: Int, order: Order) {
        val newList = currentList.toMutableList().apply {
            add(index, order)
        }
        submitList(newList)
    }

    fun removeAt(position: Int): Order {
        val order = currentList[position]
        submitList(currentList - order)
        return order
    }
}
