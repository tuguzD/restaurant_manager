package io.github.damirtugushev.restaurantmanager.presentation.repository

import android.app.Application
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.mock.MockOrderRepository

/**
 * Object for access to all repository types used in the application.
 */
@Suppress("UNCHECKED_CAST")
object RepositoryAccess {
    @JvmStatic
    val localRepository: Repository<Order>
        get() {
            if (pLocalRepository == null) {
                pLocalRepository = MockOrderRepository as Repository<Order>
            }
            return pLocalRepository!!
        }
    @JvmStatic
    fun initRoom(application: Application): RoomOrderRepository {
        val roomRepository = RoomOrderRepository(application)
        pLocalRepository = roomRepository as Repository<Order>
        return roomRepository
    }

    private var pLocalRepository: Repository<Order>? = null
}
