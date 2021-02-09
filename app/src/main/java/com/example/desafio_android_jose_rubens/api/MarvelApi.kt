package com.example.desafio_android_jose_rubens.api

import com.example.desafio_android_jose_rubens.models.response.MarvelResponse
import com.example.desafio_android_jose_rubens.models.response.ResultCharactersResponse
import com.example.desafio_android_jose_rubens.models.response.ResultComicResponse
import retrofit2.Call
import retrofit2.http.*

interface MarvelApi {

    @GET("/v1/public/characters")
    fun getCharacterList( @Query("limit") limit: String): Call<MarvelResponse<ResultCharactersResponse>>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getCharacterComics(@Path("characterId") characterId: String) : Call<MarvelResponse<ResultComicResponse>>

    companion object{
        const val PUBLIC_KEY = "c47b1d5747ea1942d6a01d5bdbd8483d"
        const val PRIVATE_KEY = "18fe762fa9730841d63ba387b377821a6d4ec2c3"
        const val BASE_URL = "https://gateway.marvel.com"

    }
}
