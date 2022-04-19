package com.example.kotlin.network

object Endpoints {
    const val baseURL: String = "https://backend.aegisrider.com/"
    const val devBaseURL: String = "https://devbackend.aegisrider.com/"
    const val registration: String = "auth/registration/"
    const val login: String = "auth/login/"
    const val lookUp: String = "auth/check-user/"
    const val resendEmail: String = "auth/resend-email/"
    const val passwordResetMail: String = "auth/send-password-reset-mail/"
    const val verifyResetCode: String = "auth/verify-reset-code/"
    const val resetPassword: String = "auth/reset-password/"
    const val googleSignIn: String = "auth/google-login/"
    const val appleSignIn: String = "auth/apple-login/"
    const val updateUser: String = "accounts/update-user/"
    const val profileImg: String = "/accounts/image-file-upload/{{user_key}}"
    const val coverImg: String = "/accounts/image-file-upload/{{user_key}}"
}