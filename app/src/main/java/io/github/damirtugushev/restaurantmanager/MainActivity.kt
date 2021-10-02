package io.github.damirtugushev.restaurantmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import io.github.damirtugushev.restaurantmanager.databinding.ActivityMainBinding
import io.github.damirtugushev.restaurantmanager.presentation.model.OrderData
import io.github.damirtugushev.restaurantmanager.presentation.repository.RepositoryAccess
import io.github.damirtugushev.restaurantmanager.presentation.repository.findById
import io.github.damirtugushev.restaurantmanager.presentation.view.order.OrderListFragmentDirections
import io.github.damirtugushev.restaurantmanager.presentation.view.snackbarShort

/**
 * Entry point of the application.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        RepositoryAccess.initRoom(application)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.bottomNavigation.setupWithNavController(navController)

        handleIntent()
    }

    private val navController: NavController
        get() {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
            return navHostFragment.navController
        }

    private fun handleIntent() {
        intent?.data?.let { data ->
            val path = data.toString()
            if (path.contains("orders")) {
                val id = path.split("/").last()
                val liveData = RepositoryAccess.localRepository.findById(id, this)
                liveData.observe(this) {
                    if (it != null) {
                        val order = when (it) {
                            is OrderData -> it
                            else -> OrderData(it)
                        }
                        val action = OrderListFragmentDirections.actionOrderItemFragment(order)
                        navController.navigate(action)
                    } else {
                        snackbarShort(binding.root) { "Such component does not exist!" }.show()
                    }
                    liveData.removeObservers(this)
                }
                return
            }
            if (path.contains("delivery")) {
                TODO("Meals to show are not ready neither in Presentation, nor in Domain")
            }
        }
    }
}
