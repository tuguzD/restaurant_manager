package io.github.damirtugushev.restaurantmanager.presentation.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.damirtugushev.restaurantmanager.domain.model.Order
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao.OrderDao
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto

/**
 * Room database which contains all the orders locally saved by user.
 *
 * @see Order
 */
@Database(entities = [OrderDto::class], version = 1, exportSchema = false)
internal abstract class RoomOrderDatabase internal constructor() : RoomDatabase() {
    abstract val ordersDao: OrderDao

    companion object {
        private const val DATABASE_NAME = "order_database"

        @Volatile
        private var INSTANCE: RoomOrderDatabase? = null

        /**
         * Get unique instance of the Room database.
         * @return unique instance of the Room database
         */
        @JvmStatic
        fun getInstance(context: Context): RoomOrderDatabase {
            if (INSTANCE == null) {
                synchronized(RoomOrderDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RoomOrderDatabase::class.java,
                            DATABASE_NAME,
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
