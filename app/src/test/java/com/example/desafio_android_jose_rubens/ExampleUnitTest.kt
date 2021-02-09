package com.example.desafio_android_jose_rubens

import com.example.desafio_android_jose_rubens.adapters.CharacterRecyclerAdapter
import com.example.desafio_android_jose_rubens.api.MarvelApi
import com.example.desafio_android_jose_rubens.api.MarvelRequestGen
import com.example.desafio_android_jose_rubens.models.response.MarvelResponse
import com.example.desafio_android_jose_rubens.models.response.ResultCharactersResponse
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var api: MarvelRequestGen

    @Test
    fun `can get characterList method` (){
        val response = api.create().getCharacterList("20").execute()
        assertNotEquals("408", response.code())
        assert(response.isSuccessful)
    }
}