package io.github.damirtugushev.restaurantmanager.presentation.model.user

import android.net.Uri

/**
 * Manager of the application.
 *
 * @see User
 */
class Manager internal constructor(
    override val username: String,
    override val email: String,
    override val password: String,
    override var imageUri: Uri?,
) : User
