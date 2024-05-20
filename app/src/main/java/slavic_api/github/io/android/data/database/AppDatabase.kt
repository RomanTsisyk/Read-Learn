package slavic_api.github.io.android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DeityDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deityDao(): DeityDao
}
