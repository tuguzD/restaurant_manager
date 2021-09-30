package io.github.damirtugushev.restaurantmanager.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import io.github.damirtugushev.restaurantmanager.MainActivity
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.FragmentOrderAddBinding
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.OrderAddViewModel
import io.github.damirtugushev.restaurantmanager.domain.model.Order

/**
 * A [Fragment] subclass for [Order] creation.
 */
class OrderAddFragment : Fragment() {
    private val viewModel: OrderAddViewModel by activityViewModels()
    private var _binding: FragmentOrderAddBinding? = null

    // This helper property is only valid between `onCreateView` and `onDestroyView`.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderAddBinding.inflate(inflater, container, false)

        val activity = requireActivity() as MainActivity
        val fab = activity.binding.fab
        fab.hide()

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

                    val fragmentManager = activity.supportFragmentManager
                    val navHostFragment =
                        fragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                    navHostFragment.navController.popBackStack()

                    fab.show()

                    snackbarShort { "Component was successfully added" }
                } catch (e: NumberFormatException) {
                    snackbarShort { "Incorrect input!" }
                }
            } else {
                snackbarShort { "Some fields are empty!" }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
