package hu.bme.aut.android.mymangaapp.persistence

import hu.bme.aut.android.mymangaapp.model.MangaData
import hu.bme.aut.android.mymangaapp.utils.MockTestUtil.mockMangaData
import hu.bme.aut.android.mymangaapp.utils.MockTestUtil.mockMangaDataList
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
        val mockDataList = mockMangaDataList()
        mangaDao.insertMangaDataList(mockDataList)

        val loadFromDB = mangaDao.getMangaDataList()
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))
    }

    @Test
    fun insertAndLoadMangaDataTest() = runBlocking {
        val mockData = mockMangaData()
        mangaDao.insertMangaData(mockData)

        val loadFromDB = mangaDao.getMangaDataList()
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }
}