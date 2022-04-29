package com.example.kotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kotlin.R
import com.example.kotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        title = ""
        with(binding) {
            /* //navHost and NavController
             val navHostFragment =
                 supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
             navController = navHostFragment.navController

             //Define appBarConfig: connect Nav component,
             val topLevelDestination = setOf(
                 R.id.landingFragment,
                 R.id.splashFragment,
                 R.id.loginFragment
             )//which id's should not have back <-
             val appBarConfiguration =
                 AppBarConfiguration(topLevelDestination)//you can add your drawerLayout

             //connect Toolbar with NavController
             toolbar.setupWithNavController(navController, appBarConfiguration)

             //connect NavView with NavController
             //   navView.setupWithNavController(navController)*/

            // Get NavHost and NavController
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController
            //Get AppBarConfig
            appBarConfiguration = AppBarConfiguration(navController.graph)

            //link actionBar with NavController
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



/*
    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
              finish()
        }
    }*/
}