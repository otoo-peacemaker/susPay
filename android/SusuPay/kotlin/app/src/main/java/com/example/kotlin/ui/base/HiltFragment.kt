package com.example.kotlin.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.factory.ViewModelFactory
import com.example.kotlin.repository.BaseRepository
import com.example.kotlin.repository.remote.RemoteDataSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class HiltFragment : Fragment() {

    open lateinit var binding: ViewDataBinding
    open lateinit var viewModel: ViewModel
    open var remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory)[getViewModel()]

        return binding.root
    }


    abstract fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding

    abstract fun getFragmentRepository(): BaseRepository
    abstract fun getViewModel(): Class<ViewModel>
}