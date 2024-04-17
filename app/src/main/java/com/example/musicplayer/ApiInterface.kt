package com.example.musicplayer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import  retrofit2.http.Query


interface ApiInterface {
    @Headers
        ("X-RapidAPI-Key:14d9948d01msh63891615d4be92fp1ca235jsn094c7468f01f"+
            "X-RapidAPI-Host:deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q")query:String): Call<Mydata>
} 