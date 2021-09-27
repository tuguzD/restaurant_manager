package io.github.damirtugushev.restaurantmanager.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import io.github.damirtugushev.restaurantmanager.MainActivity
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.FragmentOrderAddBinding
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.OrderAddViewModel
import io.github.damirtugushev.restaurantmanager.domain.model.Order

/**
 * A [Fragment] subclass for [Order] creation.
 */
class OrderAddFragment : Fragment() {
    private var _binding: FragmentOrderAddBinding? = null
    private var _viewModel: OrderAddViewModel? = null

    // This helper property is only valid between
    // `onCreateView` and `onDestroyView`.
    private val binding get() = _binding!!
    private val viewModel get() = _viewModel!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOrderAddBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this)[OrderAddViewModel::class.java]

        val activity = requireActivity() as MainActivity
        val activityBinding = activity.binding

        val buttonAdd = binding.buttonAdd
        buttonAdd.setOnClickListener {
            val tableNumber = binding.tableNumber.text.toString()
            val guestsNumber = binding.guestsNumber.text.toString()

            if (tableNumber.isNotEmpty() && guestsNumber.isNotEmpty()) {
                viewModel.addOrder(
                    tableNumber.toUByte(),
                    guestsNumber.toUByte()
                )
                val fragmentManager = activity.supportFragmentManager
                val navHostFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                navHostFragment.navController.navigate(R.id.action_component_list_fragment)

                activityBinding.fab.show()
            } else {
                Snackbar.make(binding.root, "Some fields are empty!", Snackbar.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _viewModel = null
    }
}
