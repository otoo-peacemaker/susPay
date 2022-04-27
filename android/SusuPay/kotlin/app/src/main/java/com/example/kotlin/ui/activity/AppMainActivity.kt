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
        setContentView(binding.root)
        with(binding) {

            navView.background = null
            val navView: BottomNavigationView = navView
            val navController =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_app_main) as NavHostFragment
            navView.setupWithNavController(navController.navController)
        }

        /*
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        bottomNavigationView.setupWithNavController(navHostFragment.navController)


        val navView: BottomNavigationView = binding.navView

         val navController = findNavController(R.id.nav_host_fragment_activity_app_main)
         // Passing each menu ID as a set of Ids because each
         // menu should be considered as top level destinations.
         val appBarConfiguration = AppBarConfiguration(
             setOf(
                 R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
             )
         )
         setupActionBarWithNavController(navController, appBarConfiguration)
         navView.setupWithNavController(navController)*/
    }
}