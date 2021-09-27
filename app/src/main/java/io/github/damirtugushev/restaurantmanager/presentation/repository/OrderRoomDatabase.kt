package io.github.damirtugushev.restaurantmanager.presentation.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dao.OrderDao
import io.github.damirtugushev.restaurantmanager.presentation.repository.room.dto.OrderDto
import io.github.damirtugushev.restaurantmanager.domain.model.Order

/**
 * Room database which contains all the orders locally saved by user.
 *
 * @see Order
 */
@Database(entities = [OrderDto::class], version = 1, exportSchema = false)
internal abstract class OrderRoomDatabase : RoomDatabase() {
    abstract val ordersDao: OrderDao

    companion object {
        @Volatile
        private var INSTANCE: OrderRoomDatabase? = null

        /**
         * Get unique instance of the Room database.
         * @return unique instance of the Room database
         */
        @JvmStatic
        fun getInstance(context: Context): OrderRoomDatabase {
            if (INSTANCE == null) {
                synchronized(OrderRoomDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            OrderRoomDatabase::class.java,
                            "order_database",
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
