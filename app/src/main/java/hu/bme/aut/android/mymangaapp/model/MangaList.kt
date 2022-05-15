package hu.bme.aut.android.mymangaapp.model

data class MangaList (
    val result: ResultEnum,
    val response: String,
    val data: List<Manga>,
    val limit: Integer,
    val offset: Integer,
    val total: Integer
)

