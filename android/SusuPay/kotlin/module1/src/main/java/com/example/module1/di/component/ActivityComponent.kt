package com.example.architecturaltemplate.di.component

import com.example.architecturaltemplate.di.PerActivity
import com.example.architecturaltemplate.di.module.ActivityModule
import dagger.Component

@PerActivity
@Component(modules = [ActivityModule::class])
interface ActivityComponent
