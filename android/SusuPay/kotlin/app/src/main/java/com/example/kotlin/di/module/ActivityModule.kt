package com.example.architecturaltemplate.di.module

import android.app.Activity
import android.content.Context
import com.example.architecturaltemplate.di.ActivityContext
import com.example.architecturaltemplate.di.PerActivity
import dagger.Module
import dagger.Provides

/**
 * A module to wrap the Activity state and expose it to the graph.
 */

@Module
class ActivityModule(private val activity: Activity) {

    @PerActivity
    @Provides
    @ActivityContext
    fun provideContext(): Context = activity


}
