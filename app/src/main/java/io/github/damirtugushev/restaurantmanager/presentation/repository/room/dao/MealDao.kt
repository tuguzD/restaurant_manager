package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import io.github.damirtugushev.restaurantmanager.domain.model.Meal
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.MealDto

/**
 * Data access object (Dao) for [Meal] class.
 */
@Dao
interface MealDao {
    @Query("SELECT * FROM `meal` WHERE `name` LIKE :name")
    fun findByName(name: String): LiveData<List<MealDto>>

    @Insert
    suspend fun insert(meal: MealDto)

    @Update
    suspend fun update(meal: MealDto)

    @Delete
    suspend fun delete(meal: MealDto)

    @Query("SELECT * FROM `meal`")
    fun getAll(): LiveData<List<MealDto>>

    @Query("DELETE FROM `meal`")
    suspend fun deleteAll()
}
