package hu.bme.aut.android.mymangaapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.CompositionLocalProvider
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.mymangaapp.ui.root.RootViewModel
import hu.bme.aut.android.mymangaapp.ui.theme.MangaComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @VisibleForTesting
    internal val viewModel: RootViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {

                MangaComposeTheme() {

                    MangaMainScreen()
                }
            }
        }
    }
}