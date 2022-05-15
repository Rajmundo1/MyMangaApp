package hu.bme.aut.android.mymangaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import javax.annotation.concurrent.Immutable

@Entity
@Immutable
data class MangaData (
    @PrimaryKey val id: String
){
    companion object{
        fun mock() = MangaData(
            id = UUID.randomUUID().toString()
        )
    }
}