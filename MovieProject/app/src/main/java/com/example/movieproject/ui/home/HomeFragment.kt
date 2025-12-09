package com.example.movieproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieproject.data.repository.MovieRepository
import com.example.movieproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MovieRepository.load(requireContext())

        binding.popularRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.popularRecycler.adapter =
            MovieAdapter(MovieRepository.getPopular()) { movie ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie.id)
                findNavController().navigate(action)
            }

        binding.recommendedRecycler.layoutManager =
            GridLayoutManager(requireContext(), 2)

        binding.recommendedRecycler.adapter =
            MovieGridAdapter(MovieRepository.getRecommended()) { movie ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie.id)
                findNavController().navigate(action)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
