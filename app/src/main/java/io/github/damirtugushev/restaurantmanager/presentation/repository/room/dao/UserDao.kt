package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.github.damirtugushev.restaurantmanager.presentation.model.user.User
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.UserDto

/**
 * Data Access Object for [User].
 *
 * @see User
 */
@Dao
interface UserDao : IDao<UserDto> {
    @Query("SELECT * FROM user WHERE username = :username")
    fun findByUsername(username: String): LiveData<UserDto>

    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<UserDto>>

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}
