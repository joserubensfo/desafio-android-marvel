package com.example.desafio_android_jose_rubens.models.response

class ComicResponse(
    val id: Int,
    val title: String,
    val description: String,
    val prices: ArrayList<ComicPriceResponse>,
    var highprice: Float,
    val thumbnail: ThumbResponse
)
