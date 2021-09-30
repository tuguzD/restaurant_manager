package io.github.damirtugushev.restaurantmanager.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.OrderItemBinding
import io.github.damirtugushev.restaurantmanager.domain.model.Order

/**
 * [RecyclerView.Adapter] that can display an [Order].
 */
class OrderListAdapter :
    ListAdapter<Order, OrderListAdapter.OrderViewHolder>(OrderDiffCallback) {

    inner class OrderViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            binding.run {
                tableNumber.text = root.resources.getString(
                    R.string.table_number_is, order.tableNumber)
                guestsNumber.text = root.resources.getString(
                    R.string.guests_number_is, order.guestsNumber)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = OrderItemBinding.inflate(
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
}
