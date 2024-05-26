package slavic_api.github.io.android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import slavic_api.github.io.android.data.model.Deity

@Database(entities = [Deity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deityDao(): DeityDao
}
