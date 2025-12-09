package com.example.movieproject.ui.details
import com.example.movieproject.R

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieproject.data.FavoriteManager
import com.example.movieproject.data.repository.MovieRepository
import com.example.movieproject.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private var movieId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId = arguments?.getInt("movieId") ?: -1

        val movie = MovieRepository.getMovieById(movieId)

        if (movie != null) {

            val resId = requireContext().resources.getIdentifier(
                movie.poster,
                "drawable",
                requireContext().packageName
            )

            if (resId != 0) {
                Glide.with(requireContext()).load(resId).into(binding.detailsPoster)
            }

            binding.detailsTitle.text = movie.title
            binding.detailsDescription.text =
                "This is a short description of the movie. You can replace this text with real synopsis."

            binding.addToFavorites.setOnClickListener {
                FavoriteManager.add(movie)

                val action =
                    DetailsFragmentDirections.actionDetailsFragmentToFavoritesFragment()

                val navOptions = androidx.navigation.NavOptions.Builder()
                    .setPopUpTo(
                        R.id.homeFragment,
                        inclusive = false
                    )
                    .build()

                findNavController().navigate(action, navOptions)
            }


        } else {
            binding.detailsTitle.text = "Movie Not Found :("
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
