package com.example.starwars.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.R
import com.example.starwars.databinding.DetailsFragmentBinding
import com.example.starwars.domain.entities.Cast
import com.example.starwars.domain.entities.MovieDetails
import com.example.starwars.utils.subscribe
import com.example.starwars.utils.video.YoutubeLoader
import com.example.starwars.utils.video.addOnCloseListener
import com.example.starwars.utils.video.pause
import com.example.starwars.utils.video.play
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var youtubeLoader: YoutubeLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        youtubeLoader = YoutubeLoader(lifecycle, binding.youtubePlayerView, binding.youtubeView)
        viewModel.getMovieDetail(args.movieId)
        viewModel.getVideo(args.movieId)
        subscribe(viewModel.movieLiveData) { it ->
            setAllFields(it)
            stopPlayer()
            setAdapter(it.casts)
        }

        subscribe(viewModel.videoLiveData) {
            youtubeLoader.loadVideo(it)
        }

        binding.textViewTextViewAll.setOnClickListener { it ->
            val action = DetailsFragmentDirections.actionDetailsFragmentToCastFragment(args.movieId)
            findNavController().navigate(action)
        }
    }


    private fun setAllFields(movie: MovieDetails) {
        binding.collapsingToolbar.title = movie.original_title
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.textViewTitle.text = movie.original_title
        binding.textViewTime.text = "Release date:  ${movie.release_date}"
        if (movie.runtime != null) {
            binding.textViewTime.text = "Runtime: ${movie.runtime.toString()}"
        } else {
            binding.textViewTime.text = "--"
        }

        binding.textViewTextDescription.text = movie.overview
    }

    private fun setAdapter(listCasts: List<Cast>) {
        val adapter = DetailsAdapter(listCasts) {
            val action = DetailsFragmentDirections.actionDetailsFragmentToCastFragment(it.id)
            findNavController().navigate(action)
        }
        binding.detailsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.detailsRecyclerView.adapter = adapter
    }

    private fun stopPlayer() {
        binding.appbar.addOnCloseListener(
            onClose = {
                binding.youtubePlayerView.pause()
            },
            onOpen = {
                binding.youtubePlayerView.play()
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}