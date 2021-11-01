package io.github.damirtugushev.restaurantmanager.presentation.view

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

/**
 * Get [SharedPreferences] of the application for user settings.
 */
val Activity.userSharedPreferences: SharedPreferences
    get() = getSharedPreferences("USER_SETTINGS", Context.MODE_PRIVATE)
