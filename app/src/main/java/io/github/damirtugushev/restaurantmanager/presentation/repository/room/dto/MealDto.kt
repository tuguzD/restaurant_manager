package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.damirtugushev.restaurantmanager.domain.model.Meal

/**
 * Data transfer object (Dto) for [Meal].
 */
@Entity(tableName = "meal")
data class MealDto(
    override val nanoId: String,
    override val name: String,
    override val description: String,
    @PrimaryKey(autoGenerate = true) var mid: Long,
) : Meal {
    constructor(m: Meal) : this(m.nanoId, m.name, m.description, mid = 0L)
}
