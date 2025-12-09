package com.example.movieproject.data.repository

import android.content.Context
import com.example.movieproject.model.Movie
import com.google.gson.Gson

object MovieRepository {

    data class MoviesResponse(
        val popular: List<Movie>,
        val recommended: List<Movie>
    )

    private var moviesData: MoviesResponse? = null

    fun load(context: Context) {
        try {
            val json = context.assets.open("movies.json")
                .bufferedReader()
                .use { it.readText() }

            println("JSON LOADED:\n$json")

            val parsed = Gson().fromJson(json, MoviesResponse::class.java)

            moviesData = parsed

            println("Loaded popular=${parsed.popular.size}, recommended=${parsed.recommended.size}")

        } catch (e: Exception) {
            println("ERROR IN JSON: ${e.message}")
            e.printStackTrace()
        }
    }

    fun getPopular(): List<Movie> =
        moviesData?.popular ?: emptyList()

    fun getRecommended(): List<Movie> =
        moviesData?.recommended ?: emptyList()

    fun getMovieById(id: Int): Movie? {
        val allMovies = getPopular() + getRecommended()
        return allMovies.find { it.id == id }
    }


}
