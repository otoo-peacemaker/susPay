package com.example.kotlin.ui.fragment.reset

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentResetPasswordBinding
import com.example.kotlin.util.extensions.navigateTo


class ResetPasswordFragment : Fragment(R.layout.fragment_reset_password) {
    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentResetPasswordBinding.bind(view)
        with(binding){
            resetBtn.setOnClickListener {
                navigateTo(R.id.action_resetPasswordFragment_to_loginFragment)
            }
        }
    }
}