package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import io.github.damirtugushev.restaurantmanager.presentation.model.user.User
import io.github.damirtugushev.restaurantmanager.presentation.model.user.Waiter

/**
 * Data Transfer Object for [User].
 */
@Entity(tableName = "user")
data class UserDto(
    @PrimaryKey override val username: String,
    override val email: String,
    override val password: String,
    @ColumnInfo(name = "image_uri") var imageUriString: String?,
) : Waiter(username, email, password, imageUriString?.let { Uri.parse(it) }) {
    @Ignore
    override var imageUri = super.imageUri

    /**
     * Constructs [UserDto] from the [User].
     */
    constructor(u: User) : this(u.username, u.email, u.password, u.imageUri?.toString())
}
