package com.dicoding.submissiondua.ui.details

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.submissiondua.R.drawable
import com.dicoding.submissiondua.data.entity.DetailsEntity
import com.dicoding.submissiondua.databinding.ActivityDetailsBinding
import com.dicoding.submissiondua.databinding.ActivityDetailsBinding.inflate
import com.dicoding.submissiondua.databinding.ContentDetailsBinding
import com.dicoding.submissiondua.viewmodel.ViewModelFactory.Companion.getViewModelFactory
import jp.wasabeef.glide.transformations.BlurTransformation

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAILS = "extra_details"
        const val EXTRA_SELECTED = "extra_selected"
    }

    private lateinit var activityDetailsBinding: ActivityDetailsBinding
    private lateinit var contentDetailsBinding: ContentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailsBinding = inflate(layoutInflater)
        with(activityDetailsBinding) {
            setContentView(root)
            contentDetailsBinding = details
        }

        val detailsViewModel = ViewModelProvider(this, getViewModelFactory())[DetailsViewModel::class.java]

        intent.extras.also {
            if (it != null) {
                val selectedId = it.getString(EXTRA_DETAILS)
                val selectedItems = it.getString(EXTRA_SELECTED)
                if (selectedId != null && selectedItems != null) {
                    showControl(true)

                    detailsViewModel.apply {
                        setSelected(selectedId, selectedItems)
                        getSelected().observe(this@DetailsActivity, { details ->
                            showControl(false)

                            generateDetails(details)
                        })
                    }
                }
            }
        }

        val window: Window = window
        window.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)
    }

    private fun generateDetails(entityOfDetails: DetailsEntity) {
        val genre = entityOfDetails.genre.toString()
            .replace("[", "")
            .replace("]", "")

        contentDetailsBinding.apply {
            entityOfDetails.apply {
                Glide
                    .with(this@DetailsActivity)
                    .load("https://image.tmdb.org/t/p/w500$backdrop")
                    .apply(placeholderOf(drawable.ic_loading).error(drawable.ic_error))
                    .apply(bitmapTransform(BlurTransformation(3, 6)))
                    .into(imgBackdrop)
                Glide
                    .with(this@DetailsActivity)
                    .load("https://image.tmdb.org/t/p/w500$poster")
                    .transform(RoundedCorners(36))
                    .apply(placeholderOf(drawable.ic_loading).error(drawable.ic_error))
                    .into(imgPoster)
                tvTitle.text = title
                tvRating.text = rating.toString()
                tvReleaseDate.text = releaseDate
                tvGenre.text = genre
                tvSynopsis.text = synopsis
            }
        }
    }

    private fun showControl(state: Boolean) {
        contentDetailsBinding.apply {
            when {
                state -> {
                    star.visibility = View.GONE
                    releaseDate.visibility = View.GONE
                    genre.visibility = View.GONE
                    synopsis.visibility = View.GONE
                    loading.visibility = View.VISIBLE
                }
                else -> {
                    star.visibility = View.VISIBLE
                    releaseDate.visibility = View.VISIBLE
                    genre.visibility = View.VISIBLE
                    synopsis.visibility = View.VISIBLE
                    loading.visibility = View.GONE
                }
            }
        }
    }
}