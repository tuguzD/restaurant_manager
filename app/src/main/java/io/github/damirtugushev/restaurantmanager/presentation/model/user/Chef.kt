package io.github.damirtugushev.restaurantmanager.presentation.model.user

import android.net.Uri

/**
 * Chef of the application.
 *
 * @see User
 */
class Chef internal constructor(
    override val username: String,
    override val email: String,
    override val password: String,
    override var imageUri: Uri?,
) : User
