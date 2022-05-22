package hu.bme.aut.android.mymangaapp.model

data class RefreshResponse (
    val result: ResultEnum,
    val token: Token,
    val message: String
)
