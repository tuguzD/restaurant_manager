package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.damirtugushev.restaurantmanager.domain.model.Meal
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.MealDto

/**
 * Data access object (Dao) for `Meal` class.
 *
 * @see Meal
 */

@Dao
interface MealDao {
    @Query("SELECT * FROM meal WHERE name LIKE :name")
    fun findByName(name: String): LiveData<List<MealDto>>

    @Insert
    fun addMeal(meal: MealDto)

    @Delete
    fun deleteMeal(meal: MealDto)

    @Query("SELECT * FROM meal")
    fun getAllMeals(): LiveData<List<MealDto>>

    @Query("DELETE FROM meal")
    fun deleteAllMeals()
}