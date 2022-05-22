package hu.bme.aut.android.mymangaapp.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
): ViewModel(){

    private val mangaIdSharedFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1)

    val mangaDetailsFlow = mangaIdSharedFlow.flatMapLatest{
        detailsRepository.getMangaById(it.toString())
    }

    fun loadMangaById(id: String) = mangaIdSharedFlow.tryEmit(id)
}