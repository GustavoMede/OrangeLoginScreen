package com.example.orangeloginscreen.RecyclerView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orangeloginscreen.databinding.ActivityMovieItemBinding
import com.example.orangeloginscreen.domain.model.Movie

typealias MovieListener = (Movie) -> Unit

class ListMoviesAdapter(
    private val movies: List<Movie>,
    private val listener: MovieListener
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ActivityMovieItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, listener, movies)
    }

    override fun getItemCount(): Int = movies.size
}

class MovieViewHolder(val view: ActivityMovieItemBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(movie: Movie, listener: MovieListener, movies: List<Movie>) {

        Glide.with(itemView.context).load(movie.urlBanner).into(view.imageBanner)

        view.root.setOnClickListener {
            val movieSelected = movies[adapterPosition]
            listener.invoke(movieSelected)
        }
    }
}
