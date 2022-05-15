package hu.bme.aut.android.mymangaapp.model

data class Manga(
    val id: String,
    val type: String,
    val data: MangaAttributes,
    val relationships: List<Relationship>
)
