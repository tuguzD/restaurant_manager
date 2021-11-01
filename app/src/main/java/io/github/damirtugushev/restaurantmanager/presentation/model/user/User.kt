package io.github.damirtugushev.restaurantmanager.presentation.model.user

import android.net.Uri
import io.github.damirtugushev.restaurantmanager.domain.model.Identifiable

/**
 * Base interface for users.
 */
sealed interface User : Identifiable<String> {
    val username: String
    val email: String
    val password: String
    var imageUri: Uri?

    override val nanoId get() = username
}

// TODO("обдумать ролевую модель ЕЩЁ РАЗ (но там немного)")
inline val User.role get() = when (this) {
    Admin -> "admin"
    is Moderator -> "moderator" // сделать управляющим своего ресторана
    is Manager -> "manager" // сделать пользователем, обновляющим инфу о своем ресторане
    is Waiter -> "waiter"
    is Chef -> "chef"
}
