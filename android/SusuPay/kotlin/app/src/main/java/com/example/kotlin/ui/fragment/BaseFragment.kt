package com.example.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.repository.BaseRepository
import com.example.kotlin.factory.ViewModelFactory
import com.example.kotlin.repository.remote.RemoteDataSource

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VM : ViewModel, viewBinding : ViewDataBinding, repository : BaseRepository> :
    Fragment() {

    private lateinit var binding: viewBinding
    private lateinit var viewModel: VM

    var remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getFragmentBinding(inflater, container) as viewBinding
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory)[getViewModel()]

        return binding.root
    }

    abstract fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding

    abstract fun getFragmentRepository(): repository

    abstract fun getViewModel(): Class<VM>

}