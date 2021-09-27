package io.github.damirtugushev.restaurantmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import io.github.damirtugushev.restaurantmanager.databinding.ActivityMainBinding
import io.github.damirtugushev.restaurantmanager.presentation.repository.OrderRepository

/**
 * Entry point of the application.
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    companion object {
        lateinit var repository: OrderRepository
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        repository = OrderRepository(application)

        val view = binding.root
        setContentView(view)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val fab = binding.fab
        fab.setOnClickListener {
            val fragmentContainer = binding.navHostFragment
            fragmentContainer.findNavController().navigate(R.id.action_component_add_fragment)
            fab.hide()
        }
    }
}
