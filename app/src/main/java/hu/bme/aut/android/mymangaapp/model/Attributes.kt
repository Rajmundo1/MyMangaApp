package hu.bme.aut.android.mymangaapp.model

import android.icu.text.CaseMap.Title
import java.util.*


data class Attributes (
    val title: Title,
    val altTitles: ArrayList<AltTitle>,
    val description: Any,
    val isLocked: Boolean,
    val links: Links,
    val originalLanguage: String,
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
