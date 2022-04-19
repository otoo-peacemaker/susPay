package com.example.kotlin.di.module

import android.app.Activity
import android.content.Context
import com.example.kotlin.di.Scope
import com.example.kotlin.di.context.ActivityContext
import dagger.Module
import dagger.Provides

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Scope
    @Provides
    @ActivityContext
    fun provideContext(): Context = activity


}
