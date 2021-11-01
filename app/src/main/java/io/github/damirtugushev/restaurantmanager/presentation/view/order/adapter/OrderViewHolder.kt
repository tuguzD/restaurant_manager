package io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.ItemOrderBinding
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import io.github.damirtugushev.restaurantmanager.presentation.view.order.OrderListFragmentDirections

class OrderViewHolder(private val binding: ItemOrderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var _order: OrderData
    private inline val order get() = _order

    init {
        binding.root.setOnClickListener {
            val action = OrderListFragmentDirections.actionOrderItemFragment(order)
            it.findNavController().navigate(action)
        }
    }

    fun bind(order: OrderData) {
        _order = order
        binding.run {
            tableNumber.text = root.resources.getString(
                R.string.table_number_is, order.tableNumber
            )
            guestsNumber.text = root.resources.getString(
                R.string.guests_number_is, order.guestsNumber
            )
        }
    }
}
