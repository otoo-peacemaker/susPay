package com.example.kotlin.webservices

/*@Singleton
class HttpRequest @Inject constructor(private val networkCall: ApiInterface) : AuthRepository {

    override fun login(userBody: Auths): Observable<AuthsResponse> {
        return networkCall.loginUser(userBody).subscribeOn(Schedulers.io())
            .doOnNext {
                Log.i("login", it.data.toString()) }
            .observeOn(AndroidSchedulers.mainThread())
        }

        override fun lookUp(userBody: Auths): Observable<AuthsResponse> {
            return networkCall.registerUser(userBody).subscribeOn(Schedulers.io()).doOnNext {
                Log.i("LookUp", it.data.toString())
            }.observeOn(AndroidSchedulers.mainThread())
        }
}*/



