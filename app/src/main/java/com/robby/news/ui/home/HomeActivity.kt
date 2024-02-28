package com.robby.news.ui.home

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.robby.news.R
import com.robby.news.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }

}
