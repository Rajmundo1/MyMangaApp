package hu.bme.aut.android.mymangaapp.ui.details

import androidx.annotation.WorkerThread
import hu.bme.aut.android.mymangaapp.persistence.MangaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val mangaDao: MangaDao
){

    @WorkerThread
    fun getMangaById(id: String) = flow{
        val manga = mangaDao.getMangaById(id)
        emit(manga)
    }.flowOn(Dispatchers.IO)
}