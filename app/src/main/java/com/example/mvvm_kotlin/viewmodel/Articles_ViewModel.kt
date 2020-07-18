package com.example.mvvm_kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_kotlin.pojo.Articles_
import com.example.mvvm_kotlin.pojo.UserList
import com.example.mvvm_kotlin.retrofit.APIClient
import com.example.mvvm_kotlin.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Articles_ViewModel : ViewModel() {

    var articles_List : MutableLiveData<List<Articles_>>? = null

    var userList_List : MutableLiveData<List<UserList>>? = null


    fun getArticles(page: Int, limit: String): LiveData<List<Articles_>>{

        if(articles_List == null){

            articles_List = MutableLiveData<List<Articles_>>()

            loadArticles(page, limit)
        }else{
            loadArticles(page, limit)
        }

        return articles_List as MutableLiveData<List<Articles_>>
    }

    private fun loadArticles(page: Int, limit: String) {

        Log.e("Page_VM_2 "+page, "Limit : "+limit)


        APIClient.client?.create(ApiInterface::class.java)?.getArticles(page,limit)?.enqueue(object :Callback<List<Articles_>>{
            override fun onFailure(call: Call<List<Articles_>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<Articles_>>,
                response: Response<List<Articles_>>
            ) {
                articles_List?.value = response.body()
            }
        })


    }


    fun getUserList(page: Int, limit: String): LiveData<List<UserList>>{

        if(userList_List == null){

            userList_List = MutableLiveData<List<UserList>>()

            loadUserList(page, limit)
        }else{
            loadUserList(page, limit)
        }

        return userList_List as MutableLiveData<List<UserList>>
    }

    private fun loadUserList(page: Int, limit: String) {

        Log.e("Page_VM_2 "+page, "Limit : "+limit)


        APIClient.client?.create(ApiInterface::class.java)?.getUser(page,limit)?.enqueue(object :Callback<List<UserList>>{
            override fun onFailure(call: Call<List<UserList>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<UserList>>,
                response: Response<List<UserList>>
            ) {
                userList_List?.value = response.body()
            }
        })


    }

}


