package com.example.moviegalaxy.presentation.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviegalaxy.databinding.CastFragmentBinding
import com.example.moviegalaxy.domain.entities.Cast
import com.example.moviegalaxy.utils.subscribe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastFragment : Fragment() {
    private var _binding: CastFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CastViewModel by viewModels()
    private val args: CastFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CastFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetail(args.movieId)
        subscribe(viewModel.movieLiveData) { it ->
            setAdapter(it.casts)
        }
    }

    private fun setAdapter(listCasts: List<Cast>) {
        val adapter = CastAdapter(listCasts)
        binding.castRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.castRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

