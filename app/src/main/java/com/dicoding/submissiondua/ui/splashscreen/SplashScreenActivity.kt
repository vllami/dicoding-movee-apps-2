package com.dicoding.submissiondua.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.submissiondua.R.layout
import com.dicoding.submissiondua.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }, SPLASH_SCREEN_DELAY.toLong())
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY = 3_000
    }

}