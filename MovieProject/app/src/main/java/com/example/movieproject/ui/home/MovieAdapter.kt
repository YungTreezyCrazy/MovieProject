package com.example.movieproject.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieproject.databinding.ItemMovieBinding
import com.example.movieproject.model.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        val context = holder.itemView.context

        // ищем drawable по названию
        val resId = context.resources.getIdentifier(
            movie.poster,   // cover1, cover2...
            "drawable",
            context.packageName
        )

        if (resId != 0) {
            // грузим локальную картинку
            holder.binding.movieImage.setImageResource(resId)
        } else {
            // fallback — грузим URL (если вдруг нужен)
            Glide.with(context)
                .load(movie.poster)
                .into(holder.binding.movieImage)
        }

        holder.binding.movieTitle.text = movie.title

        holder.itemView.setOnClickListener { onClick(movie) }
    }
}
