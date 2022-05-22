package hu.bme.aut.android.mymangaapp.model

data class Cover(
    val result: ResultEnum,
    val response: String,
    val data: CoverData
)
