package com.example.orangeloginscreen.RecyclerView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orangeloginscreen.databinding.ActivityMovieItemBinding
import com.example.orangeloginscreen.domain.model.Movie

typealias FavouriteMovieListener = (Movie) -> Unit

class ListFavouriteMoviesAdapter(
    private val favouriteMovies: List<Movie>,
    private val listener: FavouriteMovieListener
) : RecyclerView.Adapter<FavouriteMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteMovieViewHolder {
        val view = ActivityMovieItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return FavouriteMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteMovieViewHolder, position: Int) {
        val movie = favouriteMovies[position]
        holder.bind(movie, listener, favouriteMovies)
    }

    override fun getItemCount(): Int = favouriteMovies.size
}

class FavouriteMovieViewHolder(val view: ActivityMovieItemBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(movie: Movie, listener: FavouriteMovieListener, favouriteMovies: List<Movie>) {

        Glide.with(itemView.context).load(movie.urlBanner).into(view.imageBanner)

        view.root.setOnClickListener {
            val movieSelected = favouriteMovies[adapterPosition]
            listener.invoke(movieSelected)
        }
    }
}