package com.example.testapp.presentation.scene.tv_show_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.domain.model.TvShow
import com.example.testapp.presentation.databinding.ItemTvShowListBinding

class TvShowListAdapter : RecyclerView.Adapter<TvShowListItemViewHolder>() {

    private val data = arrayListOf<TvShow>()

    fun setData(tvShows: List<TvShow>) {
        data.clear()
        data.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowListItemViewHolder {
        val view = ItemTvShowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowListItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}