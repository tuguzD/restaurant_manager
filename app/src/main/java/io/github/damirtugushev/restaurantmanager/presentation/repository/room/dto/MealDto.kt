package io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.damirtugushev.restaurantmanager.domain.model.Meal
import io.github.damirtugushev.restaurantmanager.presentation.model.MealData

/**
 * Data transfer object (Dto) for [Meal].
 */
@Entity(tableName = "meal")
data class MealDto(
    @PrimaryKey override val nanoId: String,
    override val name: String,
    override val description: String,
    val orderNanoId: String?,
) : Meal {
    constructor(m: Meal) : this(m.nanoId, m.name, m.description, null)
    constructor(m: MealData) : this(m.nanoId, m.name, m.description, m.orderNanoId)
}
