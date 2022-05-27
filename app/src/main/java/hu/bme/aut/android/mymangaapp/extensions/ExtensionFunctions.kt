package hu.bme.aut.android.mymangaapp.extensions

import hu.bme.aut.android.mymangaapp.model.Manga
import hu.bme.aut.android.mymangaapp.model.MangaApiDataClass

fun MangaApiDataClass.toManga() = Manga(
    id = id,
    type = type,
    description = attributes?.description?.en ?: "en desc",
    state = attributes?.state ?: "State",
    year = attributes?.year.toString() ?: "year",
    title = attributes?.title?.en ?: "Title X",
    picUrl = "https://thumbs.dreamstime.com/z/raccoon-face-cute-animal-kids-cartoon-style-raccoon-face-cute-animal-kids-cartoon-style-suitable-as-placeholder-avatar-kids-t-199675791.jpg",
    coverArtId = relationships?.filter { n -> n.type == "cover_art" }[0].id,
    coverArtFileName = ""
)