package hu.bme.aut.android.mymangaapp.model

data class MangaApiDataClass(
    val id: String,
    val type: String,
    val attributes: MangaAttributes,
    val relationships: List<Relationship>)
{
}