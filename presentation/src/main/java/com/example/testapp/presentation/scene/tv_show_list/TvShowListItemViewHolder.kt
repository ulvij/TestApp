package com.example.testapp.presentation.scene.tv_show_list

import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.domain.model.TvShow
import com.example.testapp.presentation.BuildConfig
import com.example.testapp.presentation.databinding.ItemTvShowListBinding
import com.example.testapp.presentation.utils.loadUrl

class TvShowListItemViewHolder(private val item: ItemTvShowListBinding) :
    RecyclerView.ViewHolder(item.root) {

    fun bind(tvShow: TvShow) {
        item.imageTvShow.loadUrl(BuildConfig.BASE_IMAGE_URL + tvShow.backdropPath)
        item.textTvShowName.text = tvShow.name
        item.textTvShowRate.text = tvShow.voteAverage.toString()
    }
}