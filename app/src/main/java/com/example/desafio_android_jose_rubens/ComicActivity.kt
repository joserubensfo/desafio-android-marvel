package com.example.desafio_android_jose_rubens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import com.example.desafio_android_jose_rubens.api.MarvelRequestGen
import com.example.desafio_android_jose_rubens.models.response.ComicResponse
import com.example.desafio_android_jose_rubens.models.response.MarvelResponse
import com.example.desafio_android_jose_rubens.models.response.ResultComicResponse
import kotlinx.android.synthetic.main.activity_comic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)

        intent.getStringExtra("characterId")?.let { addData(it) }

        setSupportActionBar(toolbar_comic)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "Back"

        toolbar_comic.setOnClickListener(){
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addData(characterId: String, api: MarvelRequestGen = MarvelRequestGen()) {

        progressBar.setVisibility(View.VISIBLE);

        var name = intent.extras?.get("characterName").toString()
        character_name_comic.text = name

        var description = intent.extras?.get("characterDescription").toString()
        character_description_comic.text = description

        val call = api.create().getCharacterComics(characterId)

        call.enqueue(object: Callback<MarvelResponse<ResultComicResponse>> {
            override fun onResponse(
                call: Call<MarvelResponse<ResultComicResponse>>,
                response: Response<MarvelResponse<ResultComicResponse>>
            ) {

                val comics = response.body()?.data!!.comics
                if(comics != null){
                    setHighPriceComic(comics)
                    setHighComic(comics)
                }
            }

            override fun onFailure(call: Call<MarvelResponse<ResultComicResponse>>, t: Throwable) {

            }
        })
    }

    private fun setHighComic(comics: List<ComicResponse>) {
        var comic = comics[0]
        comics.forEach(){
            if (comic.highprice < it.highprice){
                comic = it
            }
        }
        setView(comic)
    }

    private fun setView(comic: ComicResponse) {
        comic_price.text = "USD" + comic.highprice.toString()
        highcomiclabel.visibility = View.VISIBLE;
        Glide.with(this)
            .load("${comic.thumbnail.path}.${comic.thumbnail.extension}")
            .into(comic_image)

        progressBar.setVisibility(View.GONE);
    }

    private fun setHighPriceComic(comics: List<ComicResponse>) {
        comics.forEachIndexed{ i, element ->
            element.highprice = comics[i].prices[0].price
            element.prices.forEach{
                if(it.price > element.highprice){
                    element.highprice = it.price
                }
            }
        }
    }
}