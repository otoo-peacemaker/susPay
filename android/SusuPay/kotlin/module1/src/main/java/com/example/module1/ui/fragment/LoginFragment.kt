package com.example.architecturaltemplate.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.architecturaltemplate.R
import com.example.architecturaltemplate.dao.LoginResponse
import com.example.architecturaltemplate.databinding.FragmentLoginBinding
import com.example.architecturaltemplate.network.AuthApi
import com.example.architecturaltemplate.network.Resource
import com.example.architecturaltemplate.repository.LoginRepository
import com.example.architecturaltemplate.util.*
import com.example.architecturaltemplate.viewmodel.LoginViewModel
import com.triad.mvvmlearning.utility.UtilityMethods
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding, LoginRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            Log.i(TAG,"...........................::::::::::::inside view model")
            binding.loading.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        binding.xmlLoginResponse = it.value// save responses to [LoginResponse]
                        viewModel.savePreference(it.value)// save responses to preference
                        view.snackbar("Login successfully")

                        TODO("Start new fragment")
                    }
                }

                is Resource.Failure -> handleApiError(it){viewModel.loginOnClickListener()}
                else -> {
                    view.snackbar("something went wrong, please try again")
                }
            }
        })

        binding.login.setOnClickListener {
            viewModel.loginOnClickListener()
        }
    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
    }

    override fun getFragmentRepository(): LoginRepository {
        return LoginRepository(remoteDataSource.buildApi(AuthApi::class.java))
    }

    override fun getViewModel() = LoginViewModel::class.java

    companion object {
       const val TAG = "LoginFragment"
    }
}