package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

/**
 * Base DAO for the application.
 */
interface IDao<T> {
    @Insert
    suspend fun insert(item: T)

    @Update
    suspend fun update(item: T)

    @Delete
    suspend fun delete(item: T)
}
