package com.example.movieproject.data

import com.example.movieproject.model.Movie

object FavoriteManager {

    private val favoriteList = mutableListOf<Movie>()

    fun add(movie: Movie) {
        if (!favoriteList.any { it.id == movie.id }) {
            favoriteList.add(movie)
        }
    }

    fun remove(movie: Movie) {
        favoriteList.removeAll { it.id == movie.id }
    }

    fun getAll(): List<Movie> {
        return favoriteList.toList()
    }
}
