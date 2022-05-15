package hu.bme.aut.android.mymangaapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.mymangaapp.R
import hu.bme.aut.android.mymangaapp.persistence.AppDatabase
import hu.bme.aut.android.mymangaapp.persistence.MangaDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                application.getString(R.string.database)
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMangaDao(appDatabase: AppDatabase): MangaDao{
        return appDatabase.mangaDao()
    }
}