package com.example.musicplayer


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView) // Corrected method name
        myAdapter = MyAdapter(this, mutableListOf()) // Initialize adapter with empty list

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitdata = retrofitBuilder.getData("eminem")
        retrofitdata.enqueue(object : Callback<Mydata?> {
            override fun onResponse(call: Call<Mydata?>, response: Response<Mydata?>) {
                val dataList = response.body()?.data
                myAdapter = myAdapter(this@MainActivity, dataList) // Update adapter data
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                Log.d("TAG:onResponse", "onResponse:" + response.body())
            }

            override fun onFailure(call: Call<Mydata?>, t: Throwable) {
                Log.d("TAG:onFailure", "onFailure:" + t.message)
            }
        })
    }
}

