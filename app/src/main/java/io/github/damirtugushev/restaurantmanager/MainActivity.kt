package io.github.damirtugushev.restaurantmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
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
            fragmentContainer.findNavController().navigate(R.id.action_component_add_fragment)
        }
    }
}
