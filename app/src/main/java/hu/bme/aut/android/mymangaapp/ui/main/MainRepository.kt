package hu.bme.aut.android.mymangaapp.ui.main

import android.icu.number.Notation.simple
import androidx.annotation.WorkerThread
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.skydoves.sandwich.onFailure
import hu.bme.aut.android.mymangaapp.model.Manga
import hu.bme.aut.android.mymangaapp.model.MangaData
import hu.bme.aut.android.mymangaapp.network.MangaService
import hu.bme.aut.android.mymangaapp.persistence.MangaDao
import com.skydoves.sandwich.suspendOnSuccess
import hu.bme.aut.android.mymangaapp.model.MangaApiDataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mangaService: MangaService,
    private val mangaDao: MangaDao,
    private val ah: String = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ0eXAiOiJzZXNzaW9uIiwiaXNzIjoibWFuZ2FkZXgub3JnIiwiYXVkIjoibWFuZ2FkZXgub3JnIiwiaWF0IjoxNjUzMjU0NDQ5LCJuYmYiOjE2NTMyNTQ0NDksImV4cCI6MTY1MzI1NTM0OSwidWlkIjoiZmRjMmE3NWItMjhhNS00NDUyLWE0ZDEtMGE3MGRlMzEyZGIzIiwicm9sIjpbIlJPTEVfVVNFUiJdLCJwcm0iOlsidXNlci5saXN0IiwicmVwb3J0LmNyZWF0ZSIsImNoYXB0ZXIudXBsb2FkIiwic2NhbmxhdGlvbl9ncm91cC5jcmVhdGUiLCJhdXRob3IuY3JlYXRlIiwibWFuZ2EuY3JlYXRlIiwicmF0aW5nLmNyZWF0ZSIsInJhdGluZy5saXN0IiwicmF0aW5nLmRlbGV0ZSIsInNldHRpbmdzLnZpZXciLCJzZXR0aW5ncy5lZGl0Iiwic2V0dGluZ3NfdGVtcGxhdGUudmlldyIsInVzZXIuYXZhaWxhYmxlIiwibWFuZ2EudmlldyIsImNoYXB0ZXIudmlldyIsImF1dGhvci52aWV3Iiwic2NhbmxhdGlvbl9ncm91cC52aWV3IiwiY292ZXJfYXJ0LnZpZXciLCJ1c2VyLnZpZXciLCJtYW5nYS5saXN0IiwibWFuZ2FfcmVsYXRpb24ubGlzdCIsImNoYXB0ZXIubGlzdCIsImF1dGhvci5saXN0Iiwic2NhbmxhdGlvbl9ncm91cC5saXN0IiwiY292ZXJfYXJ0Lmxpc3QiXSwic2lkIjoiYzAzZjNhYjgtYzE0MC00MWM2LTlhNDQtNThhMDZlMmVkNTUwIn0.ag3Iwnxax2NOeSsIm0eAPyYdrzvC004UdFW4dhF9Ki7aZhMbZprQaMENUkD7e-YgDCSDWlhE9tWLq4zu3sSD5jpdt2YqlUM-sFNwEzHhnyfMmnfT6XXczUlaeLdmlLB4oelXQluZvtrpNfsO6KrG3HsXjALnBCIDVPq8CXdTESSyjyDTgeCEexHA2ChaXqiUrUBrKh53w_qsZ1Bc_VYGjxIYKhWvpEJFbXlZFNOAGg4qCaFeAji3ASPqqTq3DsUTZftRzchh2BhinJpWEqvGNlv-AvEAT84t3u2sX5xU8fOlX5di74Udhhej3cnouIAHhBvIC0A43Zz_iOcPbQl_5iSzPt-Z6dKnJ3Hwa7lV0V-evoXHhLwMkpI90XLGZO_5JKRHtfkG5MUkVH8gGk0A0hMJoaeiodEquTdljFu184lDnlPPtHcZUGKK4Sy4-diw0V9F3iRY4ncLfuUG3cFpeO86YfWLcpZKvq0GLpHnsCLouQKf2rAjAx9eXJVcHi1xjukovWhbSrYth3-hR4JSYxTKgNXd92Ax6MUOyeWASgYguZU-FZlyC37Ona0k2Qr6Dkp6pSQDUE8RxxLjWVIsKNlu2Gpp5GwVwpRpX7wp38sMWYM990UvlABQAoX-n3glW7zC6qJ4zyml3ufcl9i2dtUu7qS2Cr5TO6fJthFnp7M"
){

    init{
        Timber.d("Injection MainRepository")
    }

    @WorkerThread
    fun loadMangas(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val mangas: List<Manga> = mangaDao.getMangaList()
        if (mangas.isEmpty()) {
            // request API network call asynchronously.
            mangaService.getManga(authHeader = ah)
                // handle the case when the API request gets a success response.
                .suspendOnSuccess {
                    val mangaList = mutableListOf<Manga>()
                    for (man in data.data){
                        var manga = man.toManga()
/*                        manga.picUrl =  getImageUrl(manga.id,
                            onStart = { _isLoading.value = true },
                            onCompletion = { _isLoading.value = false },
                            onError = { Timber.d(it) }).first()*/
                        mangaList.add(manga)
                    }
                    mangaDao.insertMangaList(mangaList)
                    emit(mangaList)
                }
                // handle the case when the API request is fails.
                // e.g. internal server error.
                .onFailure { onError(this) }
        } else {
            emit(mangas)
        }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun getImageUrl(
        id: String,
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        // request API network call asynchronously.
        mangaService.getCover(id = id,authHeader = ah)
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                emit(data.data.attributes.fileName)
            }
            // handle the case when the API request is fails.
            // e.g. internal server error.
            .onFailure { onError(this) }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab
}