package com.example.kotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin.R
import com.example.kotlin.databinding.ActivityAppMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppMainBinding.inflate(layoutInflater)
         supportActionBar?.hide()
        setContentView(binding.root)
        with(binding) {

            //   navView.background = null
            val navView: BottomNavigationView = navView
            val navController =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_app_main) as NavHostFragment
            navView.setupWithNavController(navController.navController)
        }
    }
}