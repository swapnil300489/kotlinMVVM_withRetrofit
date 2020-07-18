package com.example.mvvm_kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm_kotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfile : AppCompatActivity() {

    private var profile_url : String ? = null
    private var name : String ? = null
    private var designation : String ? = null
    private var City : String ? = null
    private var bio : String ? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        init()

    }

    private fun init() {

        profile_url     = intent.getStringExtra("profile_url")
        name            = intent.getStringExtra("name")
        designation     = intent.getStringExtra("designation")
        City            = intent.getStringExtra("City")
        bio             = intent.getStringExtra("bio")


        Picasso.get().load(profile_url).into(id_profile)
        id_userName_txt.text = name
        id_designation_txt.text = designation
        id_city.text = City
        id_about_txt.text = bio
    }
}