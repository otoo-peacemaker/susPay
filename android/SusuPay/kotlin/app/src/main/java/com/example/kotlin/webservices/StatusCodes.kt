package com.example.kotlin.webservices

object StatusCodes {
        const val success = 200
        const val created = 201
        const val userExistsButNotVerified = 320
        const val userDoesNotExist = 310
        const val userAlreadyExists = 311
        const val rideNotFound = 312
        const val invalidCSV = 313
        const val rideIsNotSupported = 314
        const val rideAlreadyExists = 315
        const val rideNoLongerExists = 316
        const val fileAlreadyExists = 317
        const val keyError = 318
        const val userAccountDeactivated = 309
        const val codeNotFound = 321
        const val deviceAlreadyExists = 322
        const val accountAlreadyExistWithAppleAuth = 326
        const val accountAlreadyExistWithGoogleAuth = 327
        const val accountAlreadyExistWithEmailAuth = 328
        const val internalServerError = 500

        const val badGateway = 502

        const val unauthorized = 401
        const val noInternetConnection = 400
    }
