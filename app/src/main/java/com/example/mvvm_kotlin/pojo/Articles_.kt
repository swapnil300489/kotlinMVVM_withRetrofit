package com.example.mvvm_kotlin.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Articles_ {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    @SerializedName("comments")
    @Expose
    var comments: Int? = null

    @SerializedName("likes")
    @Expose
    var likes: Int? = null

    @SerializedName("media")
    @Expose
    var media: List<Medium>? =
        null

    @SerializedName("user")
    @Expose
    var user: List<User>? = null

    inner class Medium {
        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("blogId")
        @Expose
        var blogId: String? = null

        @SerializedName("createdAt")
        @Expose
        var createdAt: String? = null

        @SerializedName("image")
        @Expose
        var image: String? = null

        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("url")
        @Expose
        var url: String? = null

    }

    inner class User {
        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("blogId")
        @Expose
        var blogId: String? = null

        @SerializedName("createdAt")
        @Expose
        var createdAt: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("avatar")
        @Expose
        var avatar: String? = null

        @SerializedName("lastname")
        @Expose
        var lastname: String? = null

        @SerializedName("city")
        @Expose
        var city: String? = null

        @SerializedName("designation")
        @Expose
        var designation: String? = null

        @SerializedName("about")
        @Expose
        var about: String? = null

    }
}