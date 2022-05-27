package hu.bme.aut.android.mymangaapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.bme.aut.android.mymangaapp.model.Manga
import hu.bme.aut.android.mymangaapp.model.MangaData


@Database(entities = [Manga::class], version = 5)
abstract class AppDatabase: RoomDatabase() {

    abstract fun mangaDao(): MangaDao

}