package hu.bme.aut.android.mymangaapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hu.bme.aut.android.mymangaapp.R
import hu.bme.aut.android.mymangaapp.model.Manga
import hu.bme.aut.android.mymangaapp.ui.details.Details

class RecyclerViewAdapter(private var dataSet: Array<Manga>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView
        var manga: Manga? = null

        init {
            textView = view.findViewById(R.id.mangaName)
            imageView = view.findViewById(R.id.mangaImage)

            view.setOnClickListener{
                val intent = Intent(view.context, Details::class.java)
                intent.putExtra("id", manga!!.id)
                intent.putExtra("coverArtFileName", manga!!.coverArtFileName)
                imageView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.manga_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.manga = dataSet[position];
        viewHolder.textView.text = dataSet[position].title
        Picasso.get().load(dataSet[position].picUrl).into(viewHolder.imageView);
    }

    override fun getItemCount() = dataSet.size

    fun updateDataSet(mangas: Array<Manga>){
        dataSet = mangas;
        this.notifyDataSetChanged();
    }

}