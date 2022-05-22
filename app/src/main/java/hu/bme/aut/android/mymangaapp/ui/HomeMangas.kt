package hu.bme.aut.android.mymangaapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import hu.bme.aut.android.mymangaapp.model.Manga
import hu.bme.aut.android.mymangaapp.ui.custom.StaggeredVerticalGrid
import hu.bme.aut.android.mymangaapp.ui.theme.MangaComposeTheme
import hu.bme.aut.android.mymangaapp.ui.utils.NetworkImage

@Composable
fun HomeMangas(
    modifier: Modifier = Modifier,
    mangas: List<Manga>,
    selectManga: (String) -> Unit
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background)
    ) {
        StaggeredVerticalGrid(
            maxColumnWidth = 220.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            mangas.forEach { manga ->
                HomeManga(
                    manga = manga,
                    selectManga = selectManga
                )
            }
        }
    }
}

@Composable
private fun HomeManga(
    modifier: Modifier = Modifier,
    manga: Manga,
    selectManga: (String) -> Unit = {},
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = { selectManga(manga.id) }
            ),
        color = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout {
            val (image, title, content) = createRefs()
            NetworkImage(
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    },
                url = Manga.mock().picUrl
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
                    .padding(8.dp),
                text = manga.title,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
@Preview(name = "HomeManga Light Theme")
private fun HomePosterPreviewLight() {
    MangaComposeTheme(darkTheme = false) {
        HomeManga(
            manga = Manga.mock()
        )
    }
}

@Composable
@Preview(name = "HomeManga Dark Theme")
private fun HomePosterPreviewDark() {
    MangaComposeTheme(darkTheme = true) {
        HomeManga(
            manga = Manga.mock()
        )
    }
}