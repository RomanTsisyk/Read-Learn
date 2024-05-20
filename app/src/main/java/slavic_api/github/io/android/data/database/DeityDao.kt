package slavic_api.github.io.android.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DeityDao {
    @Query("SELECT * FROM deities")
    suspend fun getAllDeities(): List<DeityDB>

    @Query("SELECT * FROM deities WHERE id = :id")
    suspend fun getDeityById(id: String): DeityDB?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeities(deities: List<DeityDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeity(deity: DeityDB)
}
