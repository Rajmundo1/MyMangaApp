package hu.bme.aut.android.mymangaapp.ui.main

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.mymangaapp.R
import hu.bme.aut.android.mymangaapp.model.Manga
import hu.bme.aut.android.mymangaapp.ui.RecyclerViewAdapter
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private val viewModel: MainViewModel by viewModels()
    var mangas: MutableList<Manga> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAnalytics = Firebase.analytics

        setContentView(R.layout.main_activity)
        super.onCreate(savedInstanceState)

        val adapter = RecyclerViewAdapter(mangas.toTypedArray());
        recyclerView.layoutManager = LinearLayoutManager(baseContext);
        recyclerView.adapter = adapter;

        viewModel.mangaList("")
            .onEach { list -> withContext(Dispatchers.Main){
                adapter.updateDataSet(list.toTypedArray())
            } }
            .launchIn(CoroutineScope(Dispatchers.IO))

        searchButton.setOnClickListener{
            //throw RuntimeException("Test Crash") // Force a crash

            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_ID, "id")
                param(FirebaseAnalytics.Param.ITEM_NAME, "button click")
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "button")
            }

            val title = searchManga.text?.toString()

            if(title != null)
                viewModel.mangaList(title)
                    .onEach { list -> withContext(Dispatchers.Main){
                        adapter.updateDataSet(list.toTypedArray())
                    } }
                    .launchIn(CoroutineScope(Dispatchers.IO))
        }
/*
        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
 */
    }
}