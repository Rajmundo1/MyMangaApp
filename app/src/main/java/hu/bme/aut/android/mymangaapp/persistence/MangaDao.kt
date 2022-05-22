package hu.bme.aut.android.mymangaapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.mymangaapp.model.Manga
import hu.bme.aut.android.mymangaapp.model.MangaData
import hu.bme.aut.android.mymangaapp.model.MangaList

@Dao
interface MangaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMangaData(mangaData: MangaData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMangaDataList(mangaDataList: List<MangaData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMangaList(mangaList: List<Manga>)

    @Query("Select * FROM MangaData")
    suspend fun getMangaDataList(): List<MangaData>

    @Query("Select * FROM Manga")
    suspend fun getMangaList(): List<Manga>

    @Query("Select * From Manga WHERE id = :paramId")
    suspend fun getMangaById(paramId: String): Manga

    @Query("Select * From MangaData WHERE id = :paramId")
    suspend fun getMangaDataById(paramId: String): MangaData
}