package com.example.movieproject.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieproject.databinding.ItemMovieGridBinding
import com.example.movieproject.model.Movie

class MovieGridAdapter(
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieGridAdapter.GridViewHolder>() {

    inner class GridViewHolder(val binding: ItemMovieGridBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding = ItemMovieGridBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GridViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val movie = movies[position]
        val context = holder.itemView.context

        val resId = context.resources.getIdentifier(
            movie.poster,
            "drawable",
            context.packageName
        )

        if (resId != 0) {
            holder.binding.movieImage.setImageResource(resId)
        } else {
            Glide.with(context)
                .load(movie.poster)
                .into(holder.binding.movieImage)
        }

        holder.binding.movieTitle.text = movie.title

        holder.itemView.setOnClickListener { onClick(movie) }
    }
}
