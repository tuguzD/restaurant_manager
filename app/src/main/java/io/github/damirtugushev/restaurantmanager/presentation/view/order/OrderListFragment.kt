package io.github.damirtugushev.restaurantmanager.presentation.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.FragmentOrderListBinding
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter.OrderListAdapter
import io.github.damirtugushev.restaurantmanager.presentation.view.MarginDecoration
import io.github.damirtugushev.restaurantmanager.presentation.view.snackbarShort
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order.OrderListViewModel

/**
 * A [Fragment] subclass which represents list of [orders][Order].
 */
class OrderListFragment : Fragment() {
    private val viewModel: OrderListViewModel by activityViewModels()

    private var _binding: FragmentOrderListBinding? = null
    // This helper properties are only valid between `onCreateView` and `onDestroyView`.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)

        val adapter = OrderListAdapter()
        binding.orderList.adapter = adapter

        val spaceSize = resources.getDimensionPixelSize(R.dimen.list_item_margin)
        binding.orderList.addItemDecoration(MarginDecoration(spaceSize))

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.ACTION_STATE_IDLE,
            ItemTouchHelper.LEFT,
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val item = adapter.removeAt(position)

                snackbarShort { "Component was successfully deleted" }.apply {
                    setAction("Undo") {
                        adapter.add(position, item)
                    }
                    addCallback(object : Snackbar.Callback() {
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            if (event != DISMISS_EVENT_ACTION)
                                viewModel.deleteOrder(item)
                        }
                    })
                }.show()
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.orderList)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_order_add_fragment)
        }

        viewModel.allOrders.observe(viewLifecycleOwner) {
            it?.let { adapter.submitList(it) }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}