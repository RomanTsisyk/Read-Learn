package slavic_api.github.io.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "deities")
data class Deity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)

data class DeitiesResponse(
    val gods: List<Deity>
)