package slavic_api.github.io.android.data.model

data class Deity(
    val id: String,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)

data class DeitiesResponse(
    val gods: List<Deity>
)