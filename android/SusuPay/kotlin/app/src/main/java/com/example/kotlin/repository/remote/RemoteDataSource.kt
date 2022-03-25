package com.example.kotlin.repository.remote

import com.example.kotlin.dao.Auths
import io.reactivex.Observable

interface BaseRepository<T: Any> {
    fun login(userBody: Auths): Observable<T>
    fun lookUp(userBody: Auths): Observable<T>
   suspend fun googleSignIn(userBody: Auths):  Observable<T>

}