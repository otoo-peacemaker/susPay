package com.example.kotlin.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentLoginBinding
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()

        with(binding){
            loginBtn.setOnClickListener {
                viewModel.loginOnClickListener()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
    }


    override fun getViewModel() = LoginViewModel::class.java

    override fun initializeViewModel() = runBlocking {
        println("What's the thread name? ${Thread.currentThread().name} ")
        return@runBlocking runVM {
            println("What's the thread name? ${Thread.currentThread().name} ")
            viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            })
        }
    }


}