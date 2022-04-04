package com.example.kotlin.repository.remote

import com.example.kotlin.dao.Auths
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.reactivex.Observable

@InstallIn(ActivityComponent::class)
@Module
abstract class BaseRepository<T : Any> {
    @Binds
    abstract fun login(userBody: Auths): Observable<T>

    @Binds
    abstract fun lookUp(userBody: Auths): Observable<T>
}