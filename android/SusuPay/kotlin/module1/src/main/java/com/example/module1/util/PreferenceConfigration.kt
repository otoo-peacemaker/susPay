package com.example.architecturaltemplate.util

import com.example.architecturaltemplate.R
import com.example.module1.di.app.App
import com.triad.mvvmlearning.utility.UtilityMethods.decrypt
import com.triad.mvvmlearning.utility.UtilityMethods.encrypt

object PreferenceConfiguration {
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