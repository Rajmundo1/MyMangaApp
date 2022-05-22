package hu.bme.aut.android.mymangaapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.android.mymangaapp.model.Manga
import hu.bme.aut.android.mymangaapp.model.MangaData


@Database(entities = [MangaData::class, Manga::class], version = 2, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun mangaDao(): MangaDao
}