package com.example.kotlin.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentLandingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : Fragment(R.layout.fragment_landing) {
    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLandingBinding.bind(view)

        with(binding) {
            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.action_landingFragment_to_loginFragment)
            }
            registerBtn.setOnClickListener {
                findNavController().navigate(R.id.action_landingFragment_to_registrationFragment)
            }
        }
    }
}