package hu.bme.aut.android.mymangaapp.model

import java.util.*
import kotlin.collections.ArrayList

data class MangaAttributes (
    val title: LocalizedString,
    val altTitles: ArrayList<AltTitle>,
    val descriptionval : LocalizedString,
    val isLockedval : Boolean,
    val links: Links,
    val originalLanguageval : String,
    val lastVolume: String,
    val lastChapter: String,
    val publicationDemographic: String,
    val status: String,
    val year: Int,
    val contentRating: String,
    val tags: ArrayList<Tag>,
    val state: String,
    val chapterNumbersResetOnNewVolume: Boolean,
    val createdAt: Date,
    val updatedAt: Date,
    val version: Int,
    val availableTranslatedLanguages: ArrayList<String>,
    val name: LocalizedString,
    val group: String,
)
