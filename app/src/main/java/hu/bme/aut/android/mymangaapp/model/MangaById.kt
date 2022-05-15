package hu.bme.aut.android.mymangaapp.model

data class MangaById(
    val result: ResultEnum,
    val response: String,
    val data: Manga,
)
