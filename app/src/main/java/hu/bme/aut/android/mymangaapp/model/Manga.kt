package hu.bme.aut.android.mymangaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.concurrent.Immutable

@Entity
@Immutable
data class Manga(
    @PrimaryKey val id: String,
    val type: String,
    val title: String,
    val description: String,
    val year: String,
    val state: String,
    var picUrl: String,
    var coverArtId: String,
    var coverArtFileName: String
){
    companion object{
        fun mock() = Manga(
            id = "Id string",
            type = "manga",
            title = "Mock title",
            year = "Mock date",
            state = "Mock state",
            description = "Mock desc",
            picUrl = "https://thumbs.dreamstime.com/z/raccoon-face-cute-animal-kids-cartoon-style-raccoon-face-cute-animal-kids-cartoon-style-suitable-as-placeholder-avatar-kids-t-199675791.jpg",
            coverArtId = "",
            coverArtFileName = ""
        )
    }
}
