@file:Suppress("DEPRECATION", "LocalVariableName")

package com.example.kotlin.util

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import org.json.JSONObject
import java.io.File
import java.net.URI
import java.net.URISyntaxException
import java.security.GeneralSecurityException
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * @author Created by Peacemaker Otoo on April 2022
 * revised from Roman
 */
object UtilityMethods {

    var pd: ProgressDialog? = null

    /**
     *
     * 1 byte = 0.0078125 kilobits
     * 1 kilobits = 0.0009765625 megabit
     *
     * @param downloadTime in milliseconds
     * @param bytesIn number of bytes downloaded
     * @return SpeedInfo containing current speed
     */
    fun calculate(downloadTime: Long, bytesIn: Long): SpeedInfo {
        //  double EDGE_THRESHOLD = 176.0;
        val BYTE_TO_KILOBIT = 0.0078125
        val KILOBIT_TO_MEGABIT = 0.0009765625
        val info = SpeedInfo()
        val bytespersecond = bytesIn * 1000 / downloadTime.toDouble()
        val kilobits = bytespersecond * BYTE_TO_KILOBIT
        megabits = kilobits * KILOBIT_TO_MEGABIT
        info.downspeed = bytespersecond
        info.kilobits = kilobits
        info.megabits = megabits
        return info
    }

    var megabits = 0.0
    fun setTokenValue(token: String) {
        var tokenVal = ""
        val year =
            Calendar.getInstance()[Calendar.YEAR].toString()
        tokenVal = year + Constants.KeyToken + token
        UserPreferences.setPreference(
            Constants.PreferenceConstants.TOKEN,
            tokenVal
        )
    }

    @SuppressLint("SimpleDateFormat")
    fun parseDateTimeTohmma(time: String?): String? {
        val inputPattern = "yyyy-MM-dd hh:mm:ss"
        val outputPattern = "h:mm a"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }

    @SuppressLint("SimpleDateFormat")
    fun parseDateTimeTodMMMyyyy(time: String?): String? {
        val inputPattern = "yyyy-MM-dd hh:mm:ss"
        val outputPattern = "d MMM yyyy"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }

    @SuppressLint("SimpleDateFormat")
    fun parseDateTimeTodMMMyyyy2(time: String?): String? {
        val inputPattern = "yyyy-MM-dd"
        val outputPattern = "d MMM yy"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        val date: Date
        var str: String? = null
        try {
            date = time?.let { inputFormat.parse(it) } as Date
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }

    fun emailValidator(email: String?): Boolean {
        val pattern: Pattern
        val EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher: Matcher? = email?.let { pattern.matcher(it) }
        return matcher?.matches() ?: false
    }


    fun getFileNameByUri(context: Context, uri: Uri): String? {
        var filepath: String? = "" //default fileName
        //Uri filePathUri = uri;
        val file: File
        when {
            uri.scheme.toString().compareTo("content") == 0 -> {
                val cursor = context.contentResolver.query(
                    uri,
                    arrayOf(
                        MediaStore.Images.ImageColumns.DATA,
                        MediaStore.Images.Media.ORIENTATION
                    ),
                    null,
                    null,
                    null
                )
                val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                val mImagePath = cursor.getString(column_index)
                cursor.close()
                filepath = mImagePath
            }
            uri.scheme!!.compareTo("file") == 0 -> {
                try {
                    file = File(URI(uri.toString()))
                    if (file.exists()) filepath = file.absolutePath
                } catch (e: URISyntaxException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                }
            }
            else -> {
                filepath = uri.path
            }
        }
        return filepath
    }

    fun sendSMS(message: String, context: Context) {


        var jsonbody: JSONObject? = null
        try {
            jsonbody = JSONObject()
            jsonbody.put("fsn", message)
            jsonbody.put(
                "device",
                UserPreferences.getPreference(Constants.PreferenceConstants.DEVICE)
            )
            jsonbody.put(
                "token",
                UserPreferences.getPreference(Constants.PreferenceConstants.TOKEN)
            )

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "9200003232"))
        intent.putExtra("sms_body", "AMZKDL $message")
        context.startActivity(intent)
    }


    fun hideKeyboard(context: Context, view: View) {
        // hide virtual keyboard
        val imm =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }

    val timeStamp: String
        get() = Date().time.toString()


    @Throws(GeneralSecurityException::class)
    fun loadPrivateKey(key64: String?): PrivateKey {
        val clear = Base64.decode(key64, Base64.DEFAULT)
        val keySpec =
            PKCS8EncodedKeySpec(clear)
        val fact = KeyFactory.getInstance("RSA")
        val priv = fact.generatePrivate(keySpec)
        Arrays.fill(clear, 0.toByte())
        return priv
    }

    @Throws(GeneralSecurityException::class)
    fun loadPublicKey(stored: String?): PublicKey {
        val authData = Base64.decode(stored, Base64.DEFAULT)
        val spec =
            X509EncodedKeySpec(authData)
        val fact = KeyFactory.getInstance("RSA")
        return fact.generatePublic(spec)
    }

    @Throws(GeneralSecurityException::class)
    fun savePrivateKey(priv: PrivateKey?): String {
        val fact = KeyFactory.getInstance("RSA")
        val spec =
            fact.getKeySpec(
                priv,
                PKCS8EncodedKeySpec::class.java
            )
        val packed = spec.encoded
        val key64 =
            String(Base64.encode(packed, Base64.DEFAULT))
        Arrays.fill(packed, 0.toByte())
        return key64
    }

    @Throws(GeneralSecurityException::class)
    fun savePublicKey(publ: PublicKey?): String {
        val fact = KeyFactory.getInstance("RSA")
        val spec =
            fact.getKeySpec(
                publ,
                X509EncodedKeySpec::class.java
            )
        return String(Base64.encode(spec.encoded, Base64.DEFAULT))
    }


    @JvmStatic
    fun encrypt(input: String?): String {
        var crypted: ByteArray? = null
        try {
            val skey = SecretKeySpec(
                Constants.EncryptKey.toByteArray(),
                "AES"
            )
            val cipher =
                Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, skey)
            if (input != null) {
                crypted = cipher.doFinal(input.toByteArray())
            } else {
                return ""
            }
        } catch (e: Exception) {
            println(e.toString())
        }


        return Base64.encodeToString(crypted, Base64.DEFAULT)
            .replace("\n", "")
    }

    @JvmStatic
    fun decrypt(input: String?): String {
        var output: ByteArray? = null
        try {
            val skey = SecretKeySpec(
                Constants.EncryptKey.toByteArray(),
                "AES"
            )
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, skey)
            output = cipher.doFinal(Base64.decode(input, Base64.DEFAULT))
        } catch (e: Exception) {
            println(e.toString())
        }
        return output?.let { String(it) } ?: ""
    }


    fun getCurrentDate(): String {
        val c = Calendar.getInstance()
        val day: Int = c.get(Calendar.DAY_OF_MONTH)
        val month: Int = c.get(Calendar.MONTH) + 1
        val year: Int = c.get(Calendar.YEAR)
        return "$day-$month-$year"
    }
    fun formatDateFromstring(
        inputFormat: String?,
        outputFormat: String?,
        inputDate: String?
    ): String {
        var parsed: Date? = null
        var outputDate = ""
        val df_input = SimpleDateFormat(inputFormat, Locale.getDefault())
        val df_output = SimpleDateFormat(outputFormat, Locale.getDefault())
        try {
            parsed = inputDate?.let { df_input.parse(it) }
            outputDate = parsed?.let { df_output.format(it) }.toString()
        } catch (e: ParseException) {
            Log.e("", "ParseException - dateFormat")
        }
        return outputDate
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val conMan = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = conMan.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) { // connected to the internet
            Toast.makeText(context, activeNetworkInfo.typeName, Toast.LENGTH_SHORT).show()
            if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true
            } else if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's authData plan
                return true
            }
        }
        return (conMan.activeNetworkInfo != null
                && conMan.activeNetworkInfo!!.isConnected)
    }

    fun tokenValue(): String {
        var tokenVal = ""
        val year = Calendar.getInstance()[Calendar.YEAR].toString()
        tokenVal =
            year + "t3Qo7xfdH1" + UserPreferences.getPreference(Constants.PreferenceConstants.TOKEN)
        tokenVal =
            year + Constants.KeyToken + UserPreferences.getPreference(Constants.PreferenceConstants.TOKEN)
        return tokenVal
    }

    /**
     * Validation of Phone Number
     */
    fun isValidPhoneNumber(target: CharSequence?): Boolean {
        return if (target == null) {
            false
        } else {
            if (target.length < 10 || target.length > 10) {
                false
            } else {
                Patterns.PHONE.matcher(target).matches()
            }
        }
    }

    fun isLocationEnabled(context: Context): Boolean {
        var locationMode = 0
        val locationProviders: String
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                locationMode = Settings.Secure.getInt(
                    context.contentResolver,
                    Settings.Secure.LOCATION_MODE
                )
            } catch (e: SettingNotFoundException) {
                e.printStackTrace()
            }
            locationMode != Settings.Secure.LOCATION_MODE_OFF
        } else {
            locationProviders = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED
            )
            !TextUtils.isEmpty(locationProviders)
        }
    }

    fun myTimeConverter(unix: String): String {
        val sdf: DateFormat =
            SimpleDateFormat("dd MMM, yyyy' 'HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("Asia/Calcutta")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = unix.toLong()
        val tz = TimeZone.getDefault()
        sdf.timeZone = tz
        return sdf.format(calendar.time)
    }

    fun showLoadingDialog(context: Context?, msg: String?) {
        if (pd == null) {
            pd = ProgressDialog(context)
            pd!!.setMessage(msg)
            pd!!.show()
            pd!!.setCancelable(false)
            pd!!.setCanceledOnTouchOutside(false)
        }
    }

    fun dismissLoadingDialog() {
        if (pd != null) {
            pd!!.dismiss()
            pd = null
        }
    }

    /**
     * Transfer Object
     * @author devil
     */
    class SpeedInfo {
        var kilobits = 0.0
        var megabits = 0.0
        var downspeed = 0.0
    }
}