package io.github.damirtugushev.restaurantmanager.presentation.model

import android.os.Parcelable
import io.github.damirtugushev.restaurantmanager.domain.model.Meal
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.MealDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealData(
    override val nanoId: String,
    override val name: String,
    override val description: String,
    val orderNanoId: String?,
) : Meal, Parcelable {
    constructor(m: Meal) : this(m.nanoId, m.name, m.description, null)
    constructor(m: MealDto) : this(m.nanoId, m.name, m.description, m.orderNanoId)
}
