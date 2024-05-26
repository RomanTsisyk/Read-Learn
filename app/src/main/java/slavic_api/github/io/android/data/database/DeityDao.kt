package slavic_api.github.io.android.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import slavic_api.github.io.android.data.model.Deity

@Dao
interface DeityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeity(deity: Deity)

    @Query("SELECT * FROM deities WHERE id = :id")
    suspend fun getDeityById(id: String): Deity?

    @Query("SELECT * FROM deities")
    suspend fun getAllDeities(): List<Deity>
}

