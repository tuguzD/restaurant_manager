package io.github.damirtugushev.restaurantmanager

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import io.github.damirtugushev.restaurantmanager.databinding.ActivityMainBinding
import io.github.damirtugushev.restaurantmanager.presentation.model.user.toUser
import io.github.damirtugushev.restaurantmanager.presentation.model.user.user
import io.github.damirtugushev.restaurantmanager.presentation.repository.RepositoryAccess
import io.github.damirtugushev.restaurantmanager.presentation.view.account.LoginActivity
import io.github.damirtugushev.restaurantmanager.presentation.view.userSharedPreferences

/**
 * Entry point of the application.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private inline val binding get() = _binding

    private val navController: NavController
        get() {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
            return navHostFragment.navController
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        RepositoryAccess.initRoom(application)

        handleUser()

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun handleUser() {
        val sharedPreferences = userSharedPreferences

        val googleAccount = GoogleSignIn.getLastSignedInAccount(this)
        val userFromGoogle = googleAccount?.toUser()
        if (userFromGoogle != null) {
            sharedPreferences.edit {
                putString("google-token", googleAccount.idToken)
            }
            RepositoryAccess.setUser(userFromGoogle, this)
            return
        }

        val username = sharedPreferences.getString("username", null)
        if (username != null) {
            val email = sharedPreferences.getString("email", null)!!
            val password = sharedPreferences.getString("password", null)!!
            val imageUri = sharedPreferences.getString("imageUri", null)
            val user = user(username, email, password, imageUri?.let { Uri.parse(it) })
            RepositoryAccess.setUser(user, this)
            return
        }

        val loginIntent = Intent(this, LoginActivity::class.java)
        val contract = ActivityResultContracts.StartActivityForResult()
        registerForActivityResult(contract) {
            if (it.resultCode == RESULT_CANCELED) {
                finish()
                return@registerForActivityResult
            }
        }.launch(loginIntent)
    }
}
