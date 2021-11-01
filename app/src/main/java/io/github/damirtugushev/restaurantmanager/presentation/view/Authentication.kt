package io.github.damirtugushev.restaurantmanager.presentation.view

import com.google.android.gms.auth.api.signin.GoogleSignInOptions

/**
 * Google sign-in options of the application.
 */
val googleSignInOptions: GoogleSignInOptions = GoogleSignInOptions
    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
