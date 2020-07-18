package com.example.mvvm_kotlin.retrofit


import com.example.mvvm_kotlin.pojo.Articles_
import com.example.mvvm_kotlin.pojo.UserList

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("blogs")
    fun getArticles(@Query("page") page : Int,
                    @Query("limit") limit:String):Call<List<Articles_>>


    @GET("users")
    fun getUser(@Query("page") page : Int,
                @Query("limit") limit:String):Call<List<UserList>>
}