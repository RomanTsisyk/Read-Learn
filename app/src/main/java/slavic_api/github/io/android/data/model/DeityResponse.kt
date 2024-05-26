package slavic_api.github.io.android.data.model

import com.google.gson.annotations.SerializedName

data class DeityResponse(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val thumbnailUrl: String = "",
    val detailImageUrl: String = "",
    val fullDescription: String = "",
    @SerializedName("attributes") val attributes: Attributes = Attributes()
)

data class Attributes(
    @SerializedName("Domains") val domains: List<String> = emptyList(),
    @SerializedName("Symbols") val symbols: List<String> = emptyList(),
    @SerializedName("Sacred Animals") val sacredAnimals: List<String> = emptyList(),
    @SerializedName("Element") val element: String = ""
)

