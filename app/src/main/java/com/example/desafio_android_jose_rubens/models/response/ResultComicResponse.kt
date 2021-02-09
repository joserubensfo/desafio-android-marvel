package com.example.desafio_android_jose_rubens.models.response

import com.google.gson.annotations.SerializedName

class ResultComicResponse (
    @SerializedName("results")
    val comics : List<ComicResponse>
    )
