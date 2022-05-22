package hu.bme.aut.android.mymangaapp.ui.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NetworkUrlPreviewProvider : PreviewParameterProvider<String> {
    override val count: Int
        get() = super.count
    override val values: Sequence<String>
        get() = sequenceOf("https://upload.wikimedia.org/wikipedia/commons/b/b1/Loading_icon.gif")
}