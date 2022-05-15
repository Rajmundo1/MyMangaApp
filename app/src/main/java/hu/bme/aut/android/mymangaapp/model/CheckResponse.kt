package hu.bme.aut.android.mymangaapp.model

data class CheckResponse (
    val result: ResultEnum,
    val isAuthenticated: Boolean,
    val roles: List<String>,
    val permissions: List<String>
)
