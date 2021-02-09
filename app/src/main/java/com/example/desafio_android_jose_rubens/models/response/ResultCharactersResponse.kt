package com.example.desafio_android_jose_rubens.models.response

import com.google.gson.annotations.SerializedName

class ResultCharactersResponse (
    @SerializedName("results")
    val characters : List<CharacterResponse>
)