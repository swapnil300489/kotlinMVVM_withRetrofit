package com.example.mvvm_kotlin.pojo

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class UserList  (
    val id : Int,
    val createdAt : String,
    val name : String,
    val avatar : String,
    val lastname : String,
    val city : String,
    val designation : String,
    val about : String
)
