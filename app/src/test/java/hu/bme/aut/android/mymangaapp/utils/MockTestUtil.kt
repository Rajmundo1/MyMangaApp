package hu.bme.aut.android.mymangaapp.utils

import hu.bme.aut.android.mymangaapp.model.MangaData

object MockTestUtil {

    fun mockMangaData() = MangaData.mock()

    fun mockMangaDataList(): List<MangaData>{
        var mockDatas = mutableListOf<MangaData>()

        repeat(5) {
            mockDatas.add(MangaData.mock())
        }

        return mockDatas
    }
}