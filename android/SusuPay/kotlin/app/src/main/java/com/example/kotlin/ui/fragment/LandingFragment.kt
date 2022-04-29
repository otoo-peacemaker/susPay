package com.example.kotlin.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentLandingBinding
import com.example.kotlin.util.extensions.dialogCloseOnBackPress
import com.example.kotlin.util.extensions.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : Fragment(R.layout.fragment_landing) {
    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLandingBinding.bind(view)

        setHasOptionsMenu(true)

        with(binding) {
            loginBtn.setOnClickListener {
                navigateTo(R.id.action_landingFragment_to_loginFragment)
            }
            registerBtn.setOnClickListener {
                navigateTo(R.id.action_landingFragment_to_registrationFragment)
            }
        }

        dialogCloseOnBackPress()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.help_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return NavigationUI.onNavDestinationSelected(
             item,
            requireView().findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

}