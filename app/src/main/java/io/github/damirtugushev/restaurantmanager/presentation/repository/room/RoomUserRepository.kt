package io.github.damirtugushev.restaurantmanager.presentation.repository.room

import androidx.lifecycle.LiveData
import io.github.damirtugushev.restaurantmanager.presentation.model.user.User
import io.github.damirtugushev.restaurantmanager.presentation.repository.Repository
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class RoomUserRepository(private val roomDatabase: RoomDatabase) :
    Repository<String, User> {

    private val userDao get() = roomDatabase.userDao

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override val allData = userDao.getAll()

    override fun findById(nanoId: String): LiveData<out User> =
        userDao.findByUsername(username = nanoId)

    override fun add(item: User) {
        val user = UserDto(item)
        coroutineScope.launch {
            userDao.insert(user)
        }
    }

    override fun update(item: User) {
        val user = UserDto(item)
        coroutineScope.launch {
            userDao.update(user)
        }
    }

    override fun remove(item: User) {
        val user = UserDto(item)
        coroutineScope.launch {
            userDao.delete(user)
        }
    }

    override fun clear() {
        coroutineScope.launch {
            userDao.deleteAll()
        }
    }
}
