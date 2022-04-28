package com.example.kotlin.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin.network.Resource
import com.example.kotlin.ui.fragment.login.LoginFragment
import com.example.kotlin.ui.fragment.register.RegistrationFragment
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

object Extension {

    //generic function to start new activity
    fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
        Intent(this, activity).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

/*//generic function to start new fragment
fun <B : Fragment> Fragment.startNewFragment(fragment: Class<B>) {
    Intent(this, fragment).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}*/


/*
fun Fragment.logout() = lifecycleScope.launch {
    if (activity is AppMainActivity) {
        (activity as AppMainActivity).performLogout()
    }
}*/

    fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun View.enable(enabled: Boolean) {
        isEnabled = enabled
        alpha = if (enabled) 1f else 0.5f
    }

    fun View.snackbar(message: String, action: (() -> Unit)? = null) {
        val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        action?.let {
            snackbar.setAction("Retry") {
                it()
            }
        }
        snackbar.show()
    }

    fun Fragment.handleApiError(
        failure: Resource.Failures,
        retry: (() -> Unit)? = null
    ) {
        when {

            failure.isNetworkError -> requireView().snackbar(
                "Connection problem",
                retry
            )

            failure.errorCode == 400 -> {
                when (this) {
                    is LoginFragment -> requireView().snackbar("success")
                    is RegistrationFragment -> requireView().snackbar("success")
                }
            }
            failure.errorCode == 502 -> {
                when (this) {
                    is LoginFragment -> requireView().snackbar("Internal server err")
                    is RegistrationFragment -> requireView().snackbar("Internal server err")
                }
            }
            failure.errorCode == 302 -> {
                when (this) {
                    is LoginFragment -> requireView().snackbar("Unauthorized access")
                    is RegistrationFragment -> requireView().snackbar("Unauthorized access")
                    // (this as BaseFragment<*, *, *>).logout()
                }
            }

            failure.errorCode == 401 -> {
                when (this) {
                    is LoginFragment -> requireView().snackbar("authentication error")
                    is RegistrationFragment -> requireView().snackbar("authentication error")
                    // (this as BaseFragment<*, *, *>).logout()
                }
            }
            else -> {
                val error = failure.errorBody?.string().toString()
                requireView().snackbar(error)
            }
        }
    }

    /**checking the current state of the graph*/
    fun Fragment.navControllerMethod(checkCurrentDestination: Boolean) {
        var currentDestination = checkCurrentDestination
        currentDestination = false
        if (currentDestination && findNavController().currentDestination == null) {
            findNavController().navigate(findNavController().graph.startDestinationId)
        } else {
            currentDestination = false
        }
    }


    fun Fragment.onBackPressedState(action: () -> Boolean) {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                this.isEnabled = action()
                if (!this.isEnabled) {
                    requireActivity().onBackPressed()
                }
            }
        })
    }


    fun Fragment.dialogCloseOnBackPress() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
                val dialog = AlertDialog.Builder(requireContext()).setMessage("").setNegativeButton(
                    "No"
                ) { _, _ ->
                    run {
                        Log.i("No", "don't close the app")
                    }
                }
                    .setPositiveButton("Ok") { _, _ ->
                        exitProcess(0)
                    }.show()
                dialog.setMessage("Do you want to close ?")
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    fun Fragment.snackBarCloseOnBackPress() {
        var backPressedTime: Long = 0
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
                val backToast = Toast.makeText(
                    requireActivity(),
                    "Press back again to leave the app.",
                    Toast.LENGTH_SHORT
                )
                if (backPressedTime + 2000 > System.currentTimeMillis()) {
                    backToast.cancel()
                    exitProcess(1)
                } else {
                    backToast.show()
                }
                backPressedTime = System.currentTimeMillis()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, onBackPressedCallback
        )
    }
}



