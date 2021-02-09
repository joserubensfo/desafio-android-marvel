package com.example.desafio_android_jose_rubens.models.response

class MarvelResponse<T> (
        var code: Int,
        var status: String,
        var data: T?
    )
