package io.github.damirtugushev.restaurantmanager.presentation.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import io.github.damirtugushev.restaurantmanager.R
import io.github.damirtugushev.restaurantmanager.databinding.FragmentMealAddBinding
import io.github.damirtugushev.restaurantmanager.domain.model.Meal
import io.github.damirtugushev.restaurantmanager.presentation.view.MarginDecoration
import io.github.damirtugushev.restaurantmanager.presentation.view.order.adapter.paging.SearchNetListAdapter
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order.MealAddViewModel
import io.github.damirtugushev.restaurantmanager.presentation.viewmodel.order.OrderItemViewModel

/**
 * A [Fragment] subclass for [Meal] addition by searching them using REST API.
 */
class MealAddFragment : Fragment() {
    private val viewModel: MealAddViewModel by viewModels()
    private val itemViewModel: OrderItemViewModel by navGraphViewModels(R.id.orders_nav_graph)

    private var _binding: FragmentMealAddBinding? = null
    // This helper properties are only valid between `onCreateView` and `onDestroyView`.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealAddBinding.inflate(inflater, container, false)

        val pagingAdapter = SearchNetListAdapter(itemViewModel)
        binding.list.adapter = pagingAdapter

        val spaceSize = resources.getDimensionPixelSize(R.dimen.list_item_margin)
        binding.list.addItemDecoration(MarginDecoration(spaceSize))

        binding.search.run {
            isSubmitButtonEnabled = true
            setIconifiedByDefault(false)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?) = true

                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query.isNullOrBlank())
                        return true
                    viewModel.searchMeals(query.trim(), 3).observe(viewLifecycleOwner) {
                        pagingAdapter.submitData(lifecycle, it)
                    }
                    return true
                }
            })
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
