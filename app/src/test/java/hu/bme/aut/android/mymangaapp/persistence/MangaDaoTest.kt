package hu.bme.aut.android.mymangaapp.persistence

import hu.bme.aut.android.mymangaapp.utils.MockTestUtil.mockManga
import hu.bme.aut.android.mymangaapp.utils.MockTestUtil.mockMangaList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class MangaDaoTest : LocalDatabase() {

    private lateinit var mangaDao: MangaDao

    @Before
    fun init() {
        mangaDao = db.mangaDao()
    }

    @Test
    fun insertAndLoadMangaDataListTest() = runBlocking {
        val mockMangaList = mockMangaList()
        mangaDao.insertMangaList(mockMangaList)

        val loadFromDB = mangaDao.getMangaList()
        assertThat(loadFromDB[0].toString(), `is`(mockMangaList[0].toString()))
    }


    @Test
    fun insertAndLoadMangaDataTest() = runBlocking {
        val mockManga = mockManga()
        mangaDao.insertMangaList(listOf(mockManga))

        val loadFromDB = mangaDao.getMangaList()
        assertThat(loadFromDB[0].toString(), `is`(mockManga.toString()))
    }

}