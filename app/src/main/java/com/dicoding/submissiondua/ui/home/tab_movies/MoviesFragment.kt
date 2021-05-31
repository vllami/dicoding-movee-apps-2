package com.dicoding.submissiondua.ui.home.tab_movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissiondua.databinding.FragmentMoviesBinding
import com.dicoding.submissiondua.databinding.FragmentMoviesBinding.inflate
import com.dicoding.submissiondua.ui.details.DetailsActivity
import com.dicoding.submissiondua.ui.details.DetailsViewModel.Companion.MOVIES_DETAILS
import com.dicoding.submissiondua.ui.home.HomeViewModel
import com.dicoding.submissiondua.viewmodel.ViewModelFactory.Companion.getViewModelFactory
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper.ORIENTATION_VERTICAL
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper.setUpOverScroll
import androidx.lifecycle.ViewModelProvider as HomeViewModelProvider

class MoviesFragment : Fragment(), MoviesAdapter.OnItemClickCallback {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = inflate(layoutInflater, container, false)

        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showControl(true)

            moviesAdapter = MoviesAdapter()

            HomeViewModelProvider(this, getViewModelFactory())[HomeViewModel::class.java].also {
                it.getMovies().observe(viewLifecycleOwner, { movies ->
                    showControl(false)

                    moviesAdapter.apply {
                        setMovies(movies)
                        notifyDataSetChanged()
                        setOnItemClickCallback(this@MoviesFragment)
                    }

                    fragmentMoviesBinding.rvMovies.apply {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = moviesAdapter

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
                    putExtra(it.EXTRA_SELECTED, MOVIES_DETAILS)
                    context?.startActivity(this@apply)
                }
            }
        }
    }

    private fun showControl(state: Boolean) {
        fragmentMoviesBinding.apply {
            when {
                state -> {
                    rvMovies.visibility = View.GONE
                    loading.visibility = View.VISIBLE
                }
                else -> {
                    rvMovies.visibility = View.VISIBLE
                    loading.visibility =View.GONE
                }
            }
        }
    }

}