package hu.bme.aut.android.mymangaapp.ui.main

import hu.bme.aut.android.mymangaapp.network.MangaService
import hu.bme.aut.android.mymangaapp.persistence.MangaDao
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mangaService: MangaService,
    private val mangaDao: MangaDao
){

    init{
        Timber.d("Injection MainRepository")
    }
}