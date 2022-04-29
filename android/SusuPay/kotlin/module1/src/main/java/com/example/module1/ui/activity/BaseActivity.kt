package com.example.module1.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architecturaltemplate.network.RemoteDataSource
import com.triad.mvvmlearning.repository.BaseRepository
import com.example.module1.factory.ViewModelFactory

abstract class BaseActivity < VM :ViewModel, B : ViewDataBinding, R : BaseRepository > : AppCompatActivity(){

    private lateinit var binding : B
    private lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getActivityBinding() as B

        val factory = ViewModelFactory(getActivityRepository())
        viewModel = ViewModelProvider(this,factory)[getViewModel()]

    }

    abstract fun getActivityBinding() : ViewDataBinding

    abstract fun getActivityRepository() : R

    abstract fun getViewModel() : Class<VM>

}