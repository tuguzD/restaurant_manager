package io.github.damirtugushev.restaurantmanager.presentation.view.delivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.github.damirtugushev.restaurantmanager.databinding.FragmentDeliveryListBinding
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.delivery.DeliveryListViewModel

/**
 * A [Fragment] subclass which represents information about
 * meals ready to deliver and time left until 30 minutes passed.
 */
class DeliveryListFragment : Fragment() {
    private val viewModel: DeliveryListViewModel by activityViewModels()

    private var _binding: FragmentDeliveryListBinding? = null

    // This helper property is only valid between `onCreateView` and `onDestroyView`.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDeliveryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
