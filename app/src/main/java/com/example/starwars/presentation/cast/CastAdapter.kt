package com.example.starwars.presentation.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.databinding.DetailItemBinding
import com.example.starwars.domain.entities.Cast
import com.example.starwars.utils.loadImage

class CastAdapter(
    val list: List<Cast>,
) : RecyclerView.Adapter<CastAdapter.CastHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.CastHolder {
        val binding = DetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastHolder(binding)
    }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CastHolder(
        private val binding: DetailItemBinding,
        private val onClicked: (Cast) -> Unit = {}
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(cast: Cast) {
            binding.nameTextView.text = cast.name
            if (cast.profile_path != null) {
                loadImage(
                    itemView.context,
                    URL_ADDRESS + cast.profile_path,
                    binding.profilePathImageView
                )
            } else {
                loadImage(
                    itemView.context,
                    R.drawable.ic_baseline_empty_cast_avatar_24,
                    binding.profilePathImageView
                )
            }
        }
    }

    companion object {
        private const val URL_ADDRESS = "https://image.tmdb.org/t/p/original"
    }
}