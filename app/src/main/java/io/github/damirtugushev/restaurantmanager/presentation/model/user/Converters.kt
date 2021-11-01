package io.github.damirtugushev.restaurantmanager.presentation.model.user

import android.net.Uri
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

/**
 * Converts Google user to [User].
 */
fun GoogleSignInAccount.toUser() = user(displayName ?: "", email ?: "", "no-password", photoUrl)

/**
 * Creates [User] from the given [username], [email], [password] and [imageUri].
 */
fun user(username: String, email: String, password: String, imageUri: Uri?): User = when {
    email == Admin.email ->
        Admin.also { it.imageUri = imageUri }
    email.endsWith("@restaurant_manager.com") ->
        Moderator(username, email, password, imageUri)
    else ->
        Waiter(username, email, password, imageUri)
}
