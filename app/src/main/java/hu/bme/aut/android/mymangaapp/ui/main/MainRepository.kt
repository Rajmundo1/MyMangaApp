package hu.bme.aut.android.mymangaapp.ui.main

import android.util.Log
import androidx.annotation.WorkerThread
import com.google.gson.Gson
import com.skydoves.sandwich.suspendOnSuccess
import hu.bme.aut.android.mymangaapp.extensions.toManga
import hu.bme.aut.android.mymangaapp.model.Login
import hu.bme.aut.android.mymangaapp.model.Manga
import hu.bme.aut.android.mymangaapp.network.MangaService
import hu.bme.aut.android.mymangaapp.persistence.MangaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mangaService: MangaService,
    private val mangaDao: MangaDao
) {

    companion object{
        lateinit var token: String
    }

    @WorkerThread
    fun getMangas(title: String) = flow{
        val mangas: MutableList<Manga> = mangaDao.getMangaList().toMutableList()
        if(mangas.isEmpty()){
            val loginData = Login(email = "battlefield1234561@gmail.com", password = "Password1", username = "Rajmundo1")

            mangaService.login(loginData).suspendOnSuccess {
                token = data.token.session
                mangaService.getManga(limit= 10, offset = 0, authHeader = "Bearer $token", title = title).suspendOnSuccess {
                    for(dat in data.data){
                        val manga = dat.toManga()
                        mangaService.getCover(manga.coverArtId, "Bearer $token").suspendOnSuccess {
                            manga.picUrl = "https://uploads.mangadex.org/covers/" + manga.id + "/" + data.data.attributes.fileName
                            manga.coverArtFileName = data.data.attributes.fileName
                        }
                        mangas.add(manga)
                    }
                }
            }

            emit(mangas)
        } else{
            emit(mangas)
        }
    }.flowOn(Dispatchers.IO)
}