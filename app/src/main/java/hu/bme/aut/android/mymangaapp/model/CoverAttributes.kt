package hu.bme.aut.android.mymangaapp.model

import java.util.*

data class CoverAttributes(
    val volume: String,
    val fileName: String,
    val description: String,
    val locale: String,
    val createdAt: Date,
    val updatedAt: Date,
    val version: Int,
    val relationships: List<Relationship>
)
