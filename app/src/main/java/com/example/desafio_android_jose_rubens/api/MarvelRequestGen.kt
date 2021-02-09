package com.example.desafio_android_jose_rubens.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest

class MarvelRequestGen {

    fun create(): MarvelApi {

        val client = OkHttpClient.Builder()
            .addInterceptor(MarvelInterceptor())
            .build()

        val api = Retrofit.Builder()
            .baseUrl(MarvelApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return api.create(MarvelApi::class.java)
    }
}