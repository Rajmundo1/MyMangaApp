package hu.bme.aut.android.mymangaapp.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.mymangaapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.manga_details.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class Details : ComponentActivity() {

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manga_details)

        val id = intent.extras!!.get("id").toString()
        val coverArtFileName = intent.extras!!.get("coverArtFileName").toString()
        viewModel.mangaDetails(id)
            .onEach { manga -> withContext(Dispatchers.Main){
                Picasso.get().load("https://uploads.mangadex.org/covers/" + manga.id + "/" + coverArtFileName).into(mangaImage);

                mangaTitle.text = manga.title
                mangaYear.text = manga.year
                mangaState.text = manga.state
                mangaDescription.text = manga.description
            } }
            .launchIn(CoroutineScope(Dispatchers.IO))
    }
}