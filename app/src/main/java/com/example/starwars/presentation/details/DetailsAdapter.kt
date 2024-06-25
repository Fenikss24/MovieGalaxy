package com.example.starwars.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.databinding.DetailItemBinding
import com.example.starwars.domain.entities.Cast
import com.example.starwars.utils.loadImage

class DetailsAdapter(
    val list: List<Cast>,
    private val onClicked: (Cast) -> Unit = {}
) : RecyclerView.Adapter<DetailsAdapter.DetailHolder>() {

    private val limit = 4

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val binding = DetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailHolder(binding, onClicked)
    }

    override fun getItemCount(): Int {
        return if (list.size > limit) {
            limit
        } else {
            list.size
        }
    }

    inner class DetailHolder(
        private val binding: DetailItemBinding,
        private val onClicked: (Cast) -> Unit = {}
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(cast: Cast) {
            binding.nameTextView.text = cast.name
            if (cast.profile_path != null) {
                loadImage(itemView.context,
                    URL_ADDRESS + cast.profile_path,
                    binding.profilePathImageView)
            } else {
                loadImage(itemView.context,
                    R.drawable.ic_baseline_empty_cast_avatar_24,
                    binding.profilePathImageView)
            }

            binding.root.setOnClickListener() {
                onClicked(cast)
            }
        }
    }

    companion object {
        private const val URL_ADDRESS = "https://image.tmdb.org/t/p/original"
    }
}