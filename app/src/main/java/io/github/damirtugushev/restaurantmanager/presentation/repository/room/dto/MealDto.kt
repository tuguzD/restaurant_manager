package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.damirtugushev.restaurantmanager.domain.model.Meal

/**
 * Data transfer object (Dto) for [Meal].
 */

@Entity(tableName = "meal")
data class MealDto(
    @PrimaryKey(autoGenerate = true) val mid: Long = 0L,
    override val name: String,
    override val description: String,
) : Meal
