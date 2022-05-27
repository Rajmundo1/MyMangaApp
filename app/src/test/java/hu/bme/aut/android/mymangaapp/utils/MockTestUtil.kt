package hu.bme.aut.android.mymangaapp.utils

import hu.bme.aut.android.mymangaapp.model.Manga
import hu.bme.aut.android.mymangaapp.model.MangaData

object MockTestUtil {

    fun mockManga() = Manga.mock()

    fun mockMangaList(): List<Manga>{
        var mockDatas = mutableListOf<Manga>()

        repeat(5) {
            mockDatas.add(Manga.mock())
        }

        return mockDatas
    }
}