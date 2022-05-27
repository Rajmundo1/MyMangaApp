package hu.bme.aut.android.mymangaapp.ui.details

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.suspendOnSuccess
import hu.bme.aut.android.mymangaapp.extensions.toManga
import hu.bme.aut.android.mymangaapp.model.Login
import hu.bme.aut.android.mymangaapp.network.MangaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val mangaService: MangaService
){
    @WorkerThread
    fun getManga(id: String) = flow{
        val loginData = Login(email = "battlefield1234561@gmail.com", password = "Password1", username = "Rajmundo1")

        mangaService.login(loginData).suspendOnSuccess {
            mangaService.getMangaFromId(id = id, "Bearer " + data.token.session).suspendOnSuccess {
                emit(data.data.toManga())
            }
        }
    }.flowOn(Dispatchers.IO)
}