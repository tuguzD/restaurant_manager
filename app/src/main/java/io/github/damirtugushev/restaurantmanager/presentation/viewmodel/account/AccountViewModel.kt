package io.github.damirtugushev.restaurantmanager.presentation.viewmodel.account

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.damirtugushev.restaurantmanager.presentation.repository.RepositoryAccess

/**
 * View model of 'Account' page.
 */
class AccountViewModel(application: Application) : AndroidViewModel(application) {
    val currentUser get() = RepositoryAccess.currentUser
}
