package com.dicoding.submissiondua.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.submissiondua.databinding.ActivityHomeBinding.inflate as ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityHomeBinding(layoutInflater).also { binding ->
            with(binding) {
                setContentView(root)

                HomePagerAdapter(this@HomeActivity, supportFragmentManager).also {
                    with(viewPager) {
                        this.adapter = it
                        tabs.setupWithViewPager(this)
                    }
                }
            }
        }
    }

}