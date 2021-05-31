package com.dicoding.submissiondua.ui.home.tab_tv_shows

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.submissiondua.R.drawable
import com.dicoding.submissiondua.data.entity.TvShowsEntity
import com.dicoding.submissiondua.databinding.ItemsTvShowsBinding
import com.dicoding.submissiondua.databinding.ItemsTvShowsBinding.inflate as ItemsTvShowsBinding

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private val listTvShows = ArrayList<TvShowsEntity>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TvShowsViewHolder {
        ItemsTvShowsBinding(from(viewGroup.context), viewGroup, false).also {
            return TvShowsViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) = holder.bind(listTvShows[position])

    override fun getItemCount(): Int = listTvShows.size

    fun setTvShows(movies: List<TvShowsEntity>?) {
        if (movies == null) return
        with(this.listTvShows) {
            clear()
            addAll(movies)
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class TvShowsViewHolder(private val binding: ItemsTvShowsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesEntity: TvShowsEntity) {
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