package com.example.architecturaltemplate.network

/**
 * @author Peacemaker N.K Otoo
 * This enum class represent loading status of the network instead of using the
 * built in progress loader.
 * How to use it?
 * [1] create a binding adapter function and set drawable file to each status.
 * [2] create a mutable live data and extend to any response class.
 * [3] bind the mutable data to an Imageview which displays the status.
 *
 * @property [Status] represent the UI state of the app
 * @property [SUCCESS] represent a successful response
 * @property [LOADING] represent the loading state of the UI
 * @exception [ERROR] shows error messages
 * */

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}