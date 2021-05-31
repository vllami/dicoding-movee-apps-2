package com.dicoding.submissiondua.ui.home.tab_movies

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.submissiondua.R.drawable
import com.dicoding.submissiondua.data.entity.MoviesEntity
import com.dicoding.submissiondua.databinding.ItemsMoviesBinding
import com.dicoding.submissiondua.databinding.ItemsMoviesBinding.inflate as ItemsMoviesBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private val listMovies = ArrayList<MoviesEntity>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesViewHolder {
        ItemsMoviesBinding(from(viewGroup.context), viewGroup, false).also {
            return MoviesViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) = holder.bind(listMovies[position])

    override fun getItemCount(): Int = listMovies.size

    fun setMovies(movies: List<MoviesEntity>?) {
        if (movies == null) return
        with(this.listMovies) {
            clear()
            addAll(movies)
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MoviesViewHolder(private val binding: ItemsMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesEntity: MoviesEntity) {
            binding.apply {
                moviesEntity.apply {
                    Glide
                        .with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500$poster")
                        .transform(RoundedCorners(18))
                        .apply(placeholderOf(drawable.ic_loading).error(drawable.ic_error))
                        .into(imgPoster)
                    tvTitle.text = title
                    tvRating.text = rating.toString()
                    tvReleaseDate.text = releaseDate

                    itemView.setOnClickListener {
                        onItemClickCallback.onItemClicked(id.toString())
                    }
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

}