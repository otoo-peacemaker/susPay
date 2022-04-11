package com.example.module1.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.example.architecturaltemplate.network.AuthApi
import com.example.architecturaltemplate.repository.LoginRepository
import com.example.architecturaltemplate.ui.activity.UIController
import com.example.architecturaltemplate.viewmodel.LoginViewModel
import com.example.module1.R
import com.example.module1.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : BaseActivity<LoginViewModel, ActivityMainBinding, LoginRepository>() {


    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         lifecycleScope.launchWhenCreated {
            delay(5000)
            startActivity(Intent(this@MainActivity, UIController::class.java))
        }
    }


    override fun getActivityBinding(): ViewDataBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun getActivityRepository(): LoginRepository {
        return LoginRepository(remoteDataSource.buildApi(AuthApi::class.java))
    }

    override fun getViewModel() = LoginViewModel::class.java
}