package com.example.kotlin.ui.fragment.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentLoginBinding
import com.example.kotlin.ui.activity.AppMainActivity
import com.example.kotlin.util.startNewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        with(binding) {
            loginBtn.setOnClickListener {
                requireActivity().startNewActivity(AppMainActivity::class.java)
            }
        }
    }
}