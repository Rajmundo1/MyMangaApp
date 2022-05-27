package hu.bme.aut.android.mymangaapp.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.mymangaapp.model.Manga
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
): ViewModel(){

    fun mangaDetails(id: String): Flow<Manga> = detailsRepository.getManga(id)
}