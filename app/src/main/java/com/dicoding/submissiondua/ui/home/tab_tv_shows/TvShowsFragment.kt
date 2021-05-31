package com.dicoding.submissiondua.ui.home.tab_tv_shows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissiondua.databinding.FragmentTvShowsBinding
import com.dicoding.submissiondua.databinding.FragmentTvShowsBinding.inflate
import com.dicoding.submissiondua.ui.details.DetailsActivity
import com.dicoding.submissiondua.ui.details.DetailsViewModel.Companion.TV_SHOWS_DETAILS
import com.dicoding.submissiondua.ui.home.HomeViewModel
import com.dicoding.submissiondua.viewmodel.ViewModelFactory.Companion.getViewModelFactory
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper.ORIENTATION_VERTICAL
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper.setUpOverScroll
import androidx.lifecycle.ViewModelProvider as HomeViewModelProvider

class TvShowsFragment : Fragment(), TvShowsAdapter.OnItemClickCallback {

    private lateinit var tvShowsAdapter: TvShowsAdapter
    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowsBinding = inflate(layoutInflater, container, false)

        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showControl(true)

            tvShowsAdapter = TvShowsAdapter()

            HomeViewModelProvider(this, getViewModelFactory())[HomeViewModel::class.java].also {
                it.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                    showControl(false)

                    tvShowsAdapter.apply {
                        setTvShows(tvShows)
                        notifyDataSetChanged()
                        setOnItemClickCallback(this@TvShowsFragment)
                    }

                    fragmentTvShowsBinding.rvTvShows.apply {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = tvShowsAdapter

                        setUpOverScroll(this, ORIENTATION_VERTICAL)
                    }
                })
            }
        }
    }

    override fun onItemClicked(id: String) {
        Intent(context, DetailsActivity::class.java).also { it ->
            it.apply {
                DetailsActivity.also {
                    putExtra(it.EXTRA_DETAILS, id)
                    putExtra(it.EXTRA_SELECTED, TV_SHOWS_DETAILS)
                    context?.startActivity(this@apply)
                }
            }
        }
    }

    private fun showControl(state: Boolean) {
        fragmentTvShowsBinding.apply {
            when {
                state -> {
                    rvTvShows.visibility = View.GONE
                    loading.visibility = View.VISIBLE
                }
                else -> {
                    rvTvShows.visibility = View.VISIBLE
                    loading.visibility = View.GONE
                }
            }
        }
    }

}