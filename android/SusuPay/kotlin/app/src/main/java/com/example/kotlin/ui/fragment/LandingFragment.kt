package com.example.kotlin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentLandingBinding

class LandingFragment : Fragment(){
     private var _binding: FragmentLandingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      //  val landingViewModel = ViewModelProvider(this)[LandingViewModel::class.java]
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        with(binding){
            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.action_landingFragment_to_loginFragment)
            }

              registerBtn.setOnClickListener {
                findNavController().navigate(R.id.action_landingFragment_to_registrationFragment)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}