package io.github.damirtugushev.restaurantmanager.presentation.repository

import android.app.Activity
import android.app.Application
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.model.user.User
import io.github.damirtugushev.restaurantmanager.presentation.repository.mock.MockOrderRepository
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.RoomDatabase
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.RoomUserRepository
import io.github.damirtugushev.restaurantmanager.presentation.view.observeOnce
import io.github.damirtugushev.restaurantmanager.presentation.view.userSharedPreferences

/**
 * Object for access to all repository types used in the application.
 */
object RepositoryAccess {
    @JvmStatic
    val localOrderRepository: Repository<String, Order>
        get() = pLocalOrderRepository
            ?: MockOrderRepository.also { pLocalOrderRepository = it }

    @JvmStatic
    val localUserRepository: Repository<String, User>
        get() = pLocalUserRepository!!

    @JvmStatic
    val currentUser: LiveData<User> get() = pCurrentUser

    @JvmStatic
    fun initRoom(application: Application) {
        val roomDatabase = RoomDatabase.getInstance(application)

        val orderRepository = RoomOrderRepository(roomDatabase)
        val userRepository = RoomUserRepository(roomDatabase)

        pLocalOrderRepository = orderRepository
        pLocalUserRepository = userRepository
    }

    @JvmStatic
    fun setUser(user: User, activity: Activity) {
        localUserRepository.findById(user.username).observeOnce {
            if (it == null) {
                localUserRepository.add(user)
            } else {
                localUserRepository.update(user)
            }
            activity.userSharedPreferences.edit {
                putString("username", user.username)
                putString("email", user.email)
                putString("password", user.password)
                putString("imageUri", user.imageUri?.toString())
            }
            pCurrentUser.value = user
        }
    }

    private var pLocalOrderRepository: Repository<String, Order>? = null
    private var pLocalUserRepository: Repository<String, User>? = null

    private val pCurrentUser = MutableLiveData<User>()
}
