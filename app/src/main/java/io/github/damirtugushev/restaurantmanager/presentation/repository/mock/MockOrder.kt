package io.github.damirtugushev.restaurantmanager.presentation.repository.mock

import io.github.damirtugushev.restaurantmanager.domain.model.Order

/**
 * [Order] subclass used for mocking.
 */
data class MockOrder(
    override val tableNumber: Byte,
    override val guestsNumber: Byte,
) : Order
