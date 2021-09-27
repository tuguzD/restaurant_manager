package io.github.damirtugushev.restaurantmanager.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.OrderItemBinding
import io.github.damirtugushev.restaurantmanager.domain.model.Order

/**
 * [RecyclerView.Adapter] that can display an [Order].
 */
class OrderListAdapter(private val data: List<Order>) :
    RecyclerView.Adapter<OrderListAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = OrderItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = data[position]
        holder.binding.run {
            tableNumber.text = root.resources.getString(
                R.string.table_number_is, item.tableNumber.toString())
            guestsNumber.text = root.resources.getString(
                R.string.guests_number_is, item.guestsNumber.toString())
        }
    }

    override fun getItemCount() = data.size

}
