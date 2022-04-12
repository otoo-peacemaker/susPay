package com.example.kotlin.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin.R

class SplashFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* lifecycleScope.launchWhenCreated {
            delay(5000)

             findNavController().navigate(R.id.action_splashFragment_to_landingFragment)
        }*/


        Handler(Looper.getMainLooper()).postDelayed({
            // hideSystemBars()
            findNavController().navigate(R.id.action_splashFragment_to_landingFragment)
        }, 3000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}