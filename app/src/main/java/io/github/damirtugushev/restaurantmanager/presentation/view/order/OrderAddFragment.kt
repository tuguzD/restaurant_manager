package io.github.damirtugushev.restaurantmanager.presentation.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.FragmentOrderAddBinding
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order.OrderAddViewModel
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.view.snackbarShort

/**
 * A [Fragment] subclass for [Order] creation.
 */
class OrderAddFragment : Fragment() {
    private val viewModel: OrderAddViewModel by activityViewModels()

    private var _binding: FragmentOrderAddBinding? = null
    // This helper property is only valid between `onCreateView` and `onDestroyView`.
    private inline val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderAddBinding.inflate(inflater, container, false)

        val buttonAdd = binding.buttonAdd
        buttonAdd.setOnClickListener {
            val tableNumber = binding.tableNumber.text.toString()
            val guestsNumber = binding.guestsNumber.text.toString()

            if (tableNumber.isNotBlank() && guestsNumber.isNotBlank()) {
                try {
                    viewModel.addOrder(
                        tableNumber.toByte(),
                        guestsNumber.toByte(),
                    )
                    val fragmentManager = requireActivity().supportFragmentManager
                    val navHostFragment =
                        fragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
                    navHostFragment.navController.popBackStack()

                    snackbarShort { "Component was successfully added" }.show()
                } catch (e: NumberFormatException) {
                    snackbarShort { "Incorrect input!" }.show()
                }
            } else {
                snackbarShort { "Some fields are empty!" }.show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
