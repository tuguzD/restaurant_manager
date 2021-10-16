package io.github.damirtugushev.restaurantmanager.presentation.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.damirtugushev.restaurantmanager.databinding.FragmentSearchRestaurantBinding

/**
 * A [Fragment] subclass for choosing a restaurant.
 */
class SearchRestaurantFragment : Fragment() {
    private var _binding: FragmentSearchRestaurantBinding? = null
    // This helper properties are only valid between `onCreateView` and `onDestroyView`.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchRestaurantBinding.inflate(inflater, container, false)

        binding.search.run {
            isSubmitButtonEnabled = true
            setIconifiedByDefault(false)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
