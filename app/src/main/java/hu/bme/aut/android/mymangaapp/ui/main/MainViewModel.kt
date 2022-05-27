package hu.bme.aut.android.mymangaapp.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.mymangaapp.model.Manga
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var mainRepository: MainRepository
) : ViewModel() {

    fun mangaList(title: String): Flow<MutableList<Manga>> = mainRepository.getMangas(title);
}