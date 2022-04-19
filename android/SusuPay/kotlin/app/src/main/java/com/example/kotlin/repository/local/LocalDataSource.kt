package com.example.kotlin.repository.local

interface LocalDataSource {
    fun setString(key: String, value: String)
    fun getString(key: String): String
}