package io.github.damirtugushev.restaurantmanager.presentation.view.order

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.FragmentOrderItemBinding
import io.github.damirtugushev.restaurantmanager.presentation.view.hasOptionsMenu

class OrderItemFragment : Fragment() {
    private val args: OrderItemFragmentArgs by navArgs()

    private var _binding: FragmentOrderItemBinding? = null

    // This helper property is only valid between
    // `onCreateView` and `onDestroyView`.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderItemBinding.inflate(inflater, container, false)
        hasOptionsMenu = true

        val order = args.order
        binding.run {
            tableNumber.text = root.resources.getString(
                R.string.table_number_is, order.tableNumber
            )
            guestsNumber.text = root.resources.getString(
                R.string.guests_number_is, order.guestsNumber
            )
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.order_item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.order_item_share -> {
            val order = args.order
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                val link = "https://android.restaurant_manager/orders/${order.nanoId}"
                putExtra(Intent.EXTRA_TEXT, link)
                type = "text/plain"
            }
            val title = getString(R.string.share)
            val shareIntent = Intent.createChooser(sendIntent, title)
            startActivity(shareIntent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
