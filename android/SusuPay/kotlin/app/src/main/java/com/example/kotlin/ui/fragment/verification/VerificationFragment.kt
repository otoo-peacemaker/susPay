package com.example.kotlin.ui.fragment.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentVerificationBinding

class VerificationFragment : Fragment() {
      private var _binding: FragmentVerificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerificationBinding.inflate(inflater, container, false)
        with(binding){
            submitBtn.setOnClickListener {
                findNavController().navigate(R.id.action_verificationFragment_to_loginFragment)
            }
        }

        return binding.root
    }
}