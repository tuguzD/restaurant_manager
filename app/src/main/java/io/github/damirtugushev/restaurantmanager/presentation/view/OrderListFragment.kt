package io.github.damirtugushev.restaurantmanager.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.damirtugushev.restaurantmanager.databinding.FragmentOrderListBinding
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.view.adapters.OrderListAdapter
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.OrderListViewModel

/**
 * A [Fragment] subclass which represents list of [orders][Order].
 */
class OrderListFragment : Fragment() {
    private var _binding: FragmentOrderListBinding? = null
    private var _viewModel: OrderListViewModel? = null

    // This helper properties are only valid between
    // `onCreateView` and `onDestroyView`.
    private val binding get() = _binding!!
    private val viewModel get() = _viewModel!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        val view = binding.root

        _viewModel = ViewModelProvider(this)[OrderListViewModel::class.java]
        viewModel.getAllOrders().observe(viewLifecycleOwner) {
            view.adapter = OrderListAdapter(it)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _viewModel = null
    }
}
