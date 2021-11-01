package io.github.damirtugushev.restaurantmanager.presentation.model.user

import android.net.Uri

/**
 * Administrator of the application.
 *
 * @see User
 */
object Admin : User {
    override val username = "Дамир Тугушев"
    override val email = "0damir.1tugushev@gmail.com"
    override val password = "admin"
    override var imageUri: Uri? = null
}
