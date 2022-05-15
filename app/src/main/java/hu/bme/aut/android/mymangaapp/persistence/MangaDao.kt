package hu.bme.aut.android.mymangaapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.mymangaapp.model.MangaData

@Dao
interface MangaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMangaData(mangaData: MangaData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMangaDataList(mangaDataList: List<MangaData>)

    @Query("Select * FROM MangaData")
    suspend fun getMangaDataList(): List<MangaData>
}