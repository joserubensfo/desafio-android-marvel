package com.example.desafio_android_jose_rubens.api

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

class MarvelInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val ts = System.currentTimeMillis().toString()
        val apikey = MarvelApi.PUBLIC_KEY
        val hashInput = "$ts${MarvelApi.PRIVATE_KEY}${MarvelApi.PUBLIC_KEY}"

        val url = chain.request().url.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", MarvelApi.PUBLIC_KEY)
            .addQueryParameter("hash", hashInput.md5())
            .build()

        val request = chain.request().newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)

    }
}