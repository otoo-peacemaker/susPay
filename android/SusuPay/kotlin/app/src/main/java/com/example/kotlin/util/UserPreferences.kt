package com.example.kotlin.util

import com.example.kotlin.di.app.App
import com.example.kotlin.R
import com.example.kotlin.util.UtilityMethods.decrypt
import com.example.kotlin.util.UtilityMethods.encrypt

object UserPreferences {
     private var sharedPreferences = App.instance?.getSharedPreferences(
        App.instance?.resources?.getString(R.string.app_name), 0
    )

    fun getPreference(key: String): String {
        if (sharedPreferences == null) {
            sharedPreferences =
                App.instance?.getSharedPreferences(
                    App.instance!!.resources.getString(R.string.app_name), 0
                )
        }
        val temp = sharedPreferences!!.getString(key, "")
        return if (temp !== "") {
            decrypt(temp)
        } else {
            ""
        }
    }

    fun setPreference(key: String, value: String?) {
        if (value !== "")
            sharedPreferences!!.edit().putString(key, encrypt(value)).apply()
    }
}