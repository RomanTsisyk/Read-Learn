package slavic_api.github.io.android.data.model

data class DeityResponse(
    val id: String,
    val name: String,
    val description: String,
    val attributes: Attributes,
    val detailImageUrl: String
)

data class Attributes(
    val Domains: List<String>,
    val Symbols: List<String>,
    val SacredAnimals: List<String>,
    val Element: String
)