package io.github.damirtugushev.restaurantmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import io.github.damirtugushev.restaurantmanager.databinding.ActivityMainBinding
import io.github.damirtugushev.restaurantmanager.presentation.repository.RepositoryAccess

/**
 * Entry point of the application.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        RepositoryAccess.initRoom(application)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val fragmentContainer = binding.navHostFragment
            fragmentContainer.findNavController().navigate(R.id.action_order_add_fragment)
        }

        intent.data?.let {
//            val parts = it.toString().split("/")

            val navController = run {
                val navHostFragment = supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                navHostFragment.navController
            }
            navController.navigate(R.id.action_order_add_fragment)
        }
    }
}
