package slavic_api.github.io.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class DeitiesResponse(
    val gods: List<DeityResponse> = emptyList()
)

fun DeityResponse.toDeity(): Deity {
    return Deity(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        description = this.description.orEmpty(),
        thumbnailUrl = this.thumbnailUrl.orEmpty(),
        detailImageUrl = this.detailImageUrl.orEmpty(),
        fullDescription = this.fullDescription.orEmpty(),
        domains = this.attributes.domains.orEmpty(),
        symbols = this.attributes.symbols.orEmpty(),
        sacredAnimals = this.attributes.sacredAnimals.orEmpty(),
        element = this.attributes.element.orEmpty()
    )
}
