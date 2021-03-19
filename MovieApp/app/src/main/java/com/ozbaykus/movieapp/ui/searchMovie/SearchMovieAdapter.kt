package com.ozbaykus.movieapp.ui.searchMovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozbaykus.movieapp.R
import com.ozbaykus.movieapp.model.Search

class SearchMovieAdapter(
    var searchResultList: List<Search>
) : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {
    private lateinit var itemClickListener: SearchMovieViewHolder.SearchMovieItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_movie, parent, false)
        return SearchMovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchResultList.size
    }

    fun setOnItemClickListener(itemClickListener: SearchMovieViewHolder.SearchMovieItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bindItems(searchResultList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(
                searchResultList[position]
            )
        }
    }

    class SearchMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewMovieName: TextView = itemView.findViewById(R.id.textViewMovieName)
        private val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        fun bindItems(item: Search) {
            textViewMovieName.text = item.title
            Glide.with(itemView.context).load(item.poster).into(imageViewPoster);
        }

        interface SearchMovieItemClickListener {
            fun onItemClick(search: Search)
        }
    }
}

