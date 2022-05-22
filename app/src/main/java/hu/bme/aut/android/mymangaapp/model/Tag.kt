package hu.bme.aut.android.mymangaapp.model

data class Tag (
    val id: String,
    val type: String,
    val attributes: Attributes,
    val relationships: ArrayList<Any>
)