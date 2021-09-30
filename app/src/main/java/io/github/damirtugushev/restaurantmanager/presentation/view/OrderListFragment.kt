package io.github.damirtugushev.restaurantmanager.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import io.github.damirtugushev.restaurantmanager.MainActivity
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.FragmentOrderListBinding
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.view.adapters.OrderListAdapter
import io.github.damirtugushev.restaurantmanager.presentation.view.decorations.MarginDecoration
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.OrderListViewModel

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

        val view = binding.root
        val activity = requireActivity() as MainActivity
        activity.binding.fab.show()

        val adapter = OrderListAdapter()
        view.adapter = adapter

        val spaceSize = resources.getDimensionPixelSize(R.dimen.list_item_margin)
        view.addItemDecoration(MarginDecoration(spaceSize))

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
                viewModel.deleteOrder(adapter.currentList[position])

                snackbarShort { "Component was successfully deleted" }
            }
        })
        itemTouchHelper.attachToRecyclerView(view)

        viewModel.getAllOrders().observe(viewLifecycleOwner) {
            it?.let { adapter.submitList(it) }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
