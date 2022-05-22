package hu.bme.aut.android.mymangaapp.model

data class MangaList (
    val result: ResultEnum,
    val response: String,
    val data: List<MangaApiDataClass>,
    val limit: Int,
    val offset: Int,
    val total: Int
)

