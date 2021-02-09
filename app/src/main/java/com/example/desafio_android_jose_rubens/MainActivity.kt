package com.example.desafio_android_jose_rubens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android_jose_rubens.adapters.CharacterRecyclerAdapter
import com.example.desafio_android_jose_rubens.api.MarvelRequestGen
import com.example.desafio_android_jose_rubens.models.response.MarvelResponse
import com.example.desafio_android_jose_rubens.models.response.ResultCharactersResponse
import com.example.desafio_android_jose_rubens.util.ItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*

class MainActivity : AppCompatActivity() {

    private lateinit var characterAdapter : CharacterRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addDataRecyclerView()
    }

    private fun addDataRecyclerView(api:MarvelRequestGen = MarvelRequestGen()){

        val call = api.create().getCharacterList("20")
                call.enqueue(object: Callback<MarvelResponse<ResultCharactersResponse>>{
                override fun onResponse(
                    call: Call<MarvelResponse<ResultCharactersResponse>>,
                    response: Response<MarvelResponse<ResultCharactersResponse>>
                ) {
                    characterAdapter.submitList(response.body()?.data!!.characters)
                    characterAdapter.notifyDataSetChanged()
                }

                override fun onFailure(
                    call: Call<MarvelResponse<ResultCharactersResponse>>,
                    t: Throwable
                ) {
                    TODO("Not yet implemented")
                }

                })
    }

    private fun initRecyclerView(){
        recycler_view.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            val paddingDecoration = ItemDecoration(30)
            addItemDecoration(paddingDecoration)
            characterAdapter = CharacterRecyclerAdapter()
            adapter = characterAdapter
        }
    }
}