package com.example.kotlin.util.extensions

import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin.R
import com.example.kotlin.network.Resource
import com.example.kotlin.ui.fragment.login.LoginFragment
import com.example.kotlin.ui.fragment.register.RegistrationFragment
import kotlin.system.exitProcess


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
fun Fragment.checkNavDestination(checkCurrentDestination: Boolean) {
    var currentDestination = checkCurrentDestination
    currentDestination = false
    if (currentDestination && findNavController().currentDestination == null) {
        findNavController().navigate(findNavController().graph.startDestinationId)
    } else {
        currentDestination = false
    }
}


/**
 * A method to perform navigation
 * @param navHost : Pass the id nav_host_fragment in your xml file
 * @param action : Pass the id of the navigation direction from A to B
 * */
fun Fragment.navigateTo(action: Int) {
    val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController
    navController.navigate(action)

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

/**call this on OnCreate method or onCreateView*/
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