package slavic_api.github.io.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import slavic_api.github.io.android.utils.Converters

@Entity(tableName = "deities")
@TypeConverters(Converters::class)
data class Deity(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val description: String = "",
    val thumbnailUrl: String = "",
    val detailImageUrl: String = "",
    val fullDescription: String = "",
    @TypeConverters(Converters::class)
    val domains: List<String> = emptyList(),
    @TypeConverters(Converters::class)
    val symbols: List<String> = emptyList(),
    @TypeConverters(Converters::class)
    val sacredAnimals: List<String> = emptyList(),
    val element: String = ""
)
