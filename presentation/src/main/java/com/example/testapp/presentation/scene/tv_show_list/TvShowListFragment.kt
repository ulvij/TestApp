package com.example.testapp.presentation.scene.tv_show_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.presentation.base.BaseFragment
import com.example.testapp.presentation.databinding.FragmentTvShowListBinding
import com.example.testapp.presentation.utils.PaginationListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowListFragment :
    BaseFragment<TvShowListState, Unit, TvShowListViewModel, FragmentTvShowListBinding>() {

    override val bindingCallback: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTvShowListBinding
        get() = FragmentTvShowListBinding::inflate

    override val viewModel: TvShowListViewModel by viewModels()

    private val adapter: TvShowListAdapter by lazy { TvShowListAdapter() }

    override val bindViews: FragmentTvShowListBinding.() -> Unit = {
        recyclerTvShows.adapter = adapter
        recyclerTvShows.addOnScrollListener(object :
            PaginationListener(recyclerTvShows.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.loadData()
            }

            override fun isLastPage() = viewModel.isLastPage()

            override fun isLoading() = viewModel.isLoading()
        })

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.reloadData()
        }
    }

    override fun observeState(state: TvShowListState) {
        when (state) {
            is TvShowListState.ShowTvShows -> {
                adapter.setData(state.tvShows)
            }
        }
    }

    override fun showLoading() {
        super.showLoading()
        binding.progressBar.isVisible = true
    }

    override fun hideLoading() {
        super.hideLoading()
        binding.progressBar.isVisible = false
        binding.swipeRefreshLayout.isRefreshing = false
    }


}