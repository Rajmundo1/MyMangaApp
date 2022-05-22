package hu.bme.aut.android.mymangaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hu.bme.aut.android.mymangaapp.network.MangaService
import hu.bme.aut.android.mymangaapp.persistence.MangaDao
import hu.bme.aut.android.mymangaapp.ui.main.MainRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        mangaService: MangaService,
        mangaDao: MangaDao
    ): MainRepository {
        return MainRepository(mangaService, mangaDao)
    }
}