package hu.bme.aut.android.mymangaapp.model

data class MangaApiDataClass(
    val id: String,
    val type: String,
    val data: MangaAttributes,
    val relationships: List<Relationship>)
{
    fun toManga() = Manga(
        id = id,
        type = type,
        description = data?.descriptionval?.en ?: "en desc",
        state = data?.state ?: "State",
        year = data?.year.toString() ?: "year",
        title = data?.name?.en ?: "Title X",
        picUrl = "https://thumbs.dreamstime.com/z/raccoon-face-cute-animal-kids-cartoon-style-raccoon-face-cute-animal-kids-cartoon-style-suitable-as-placeholder-avatar-kids-t-199675791.jpg"
    )
}