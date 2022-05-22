package hu.bme.aut.android.mymangaapp.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.palette.graphics.Palette
import hu.bme.aut.android.mymangaapp.model.MangaData
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.material.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import hu.bme.aut.android.mymangaapp.model.Manga

@Composable
fun MangaDetails(
    posterId: String,
    viewModel: DetailsViewModel,
    pressOnBack: () -> Unit = {}
) {
    LaunchedEffect(key1 = posterId) {
        viewModel.loadMangaById(posterId)
    }

    val details: Manga? by viewModel.mangaDetailsFlow.collectAsState(initial = null)
    details?.let { manga ->
        MangaDataDetailsBody(manga, pressOnBack)
    }
}

@Composable
private fun MangaDataDetailsBody(
    manga: Manga,
    pressOnBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background)
            .fillMaxHeight()
    ) {
        var palette by remember { mutableStateOf<Palette?>(null) }

        ConstraintLayout {
            val (arrow, image, paletteRow, title, content, gifTitle, gif) = createRefs()

            Text(
                text = "Test Text",
                style = MaterialTheme.typography.h1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(paletteRow.bottom)
                    }
                    .padding(start = 16.dp, top = 12.dp)
            )

            /*NetworkImage(
                url = poster.poster,
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .aspectRatio(0.85f),
                circularRevealEnabled = true,
                bitmapPalette = BitmapPalette {
                    palette = it
                }
            )

            ColorPalettes(
                palette = palette,
                modifier = Modifier
                    .constrainAs(paletteRow) {
                        top.linkTo(image.bottom)
                    }
            )

            Text(
                text = poster.name,
                style = MaterialTheme.typography.h1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(paletteRow.bottom)
                    }
                    .padding(start = 16.dp, top = 12.dp)
            )

            Text(
                text = poster.description,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(title.bottom)
                    }
                    .padding(16.dp)
            )

            Text(
                text = "Gif",
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(gifTitle) {
                        top.linkTo(content.bottom)
                    }
            )

            CoilImage(
                imageModel = poster.gif,
                shimmerParams = ShimmerParams(
                    baseColor = background800,
                    highlightColor = shimmerHighLight
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(500.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .constrainAs(gif) {
                        top.linkTo(gifTitle.bottom)
                    },
            )

            ImageBalloonAnchor(
                reference = image,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.85f),
                content = poster.name,
                onClick = { balloon, anchor -> balloon.showAlignBottom(anchor) }
            )

            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(arrow) {
                        top.linkTo(parent.top)
                    }
                    .padding(12.dp)
                    .clickable(onClick = { pressOnBack() })
            )*/
        }
    }
}

/*@Composable
private fun ColorPalettes(
    palette: Palette?,
    modifier: Modifier
) {
    val colorList: List<Int> = palette.paletteColorList()
    LazyRow(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        items(colorList) { color ->
            Crossfade(
                targetState = color,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(45.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color(it))
                        .fillMaxSize()
                )
            }
        }
    }
}*/
