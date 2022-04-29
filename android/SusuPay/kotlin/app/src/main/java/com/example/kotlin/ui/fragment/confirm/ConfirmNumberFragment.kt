package com.example.kotlin.ui.fragment.confirm

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentConfirmNumberBinding
import com.example.kotlin.util.extensions.navigateTo

class ConfirmNumberFragment : Fragment(R.layout.fragment_confirm_number) {

    private var _binding: FragmentConfirmNumberBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentConfirmNumberBinding.bind(view)
        with(binding) {
            nextBtn.setOnClickListener {
                navigateTo(R.id.action_confirmNumberFragment_to_resetPasswordFragment)
            }
        }

    }


}