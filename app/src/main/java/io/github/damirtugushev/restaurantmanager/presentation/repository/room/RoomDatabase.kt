package io.github.damirtugushev.restaurantmanager.presentation.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao.OrderDao
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao.UserDao
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.UserDto

private typealias BaseRoomDatabase = androidx.room.RoomDatabase

@Database(
    entities = [
        UserDto::class,
        OrderDto::class,
    ],
    version = 1,
    exportSchema = false,
)
internal abstract class RoomDatabase : BaseRoomDatabase() {
    abstract val orderDao: OrderDao
    abstract val userDao: UserDao

    companion object {
        private const val DATABASE_NAME = "restaurant_manager"

        @Volatile
        private var INSTANCE: RoomDatabase? = null

        /**
         * Get unique instance of the Room database.
         * @return unique instance of the Room database
         */
        @JvmStatic
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                RoomDatabase::class.java,
                DATABASE_NAME,
            ).build().also { INSTANCE = it }
        }
    }
}
