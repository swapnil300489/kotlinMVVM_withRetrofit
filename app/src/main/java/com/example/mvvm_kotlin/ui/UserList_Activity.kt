package com.example.mvvm_kotlin.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_kotlin.R
import com.example.mvvm_kotlin.adapter.UserList_Adapter
import com.example.mvvm_kotlin.pojo.UserList
import com.example.mvvm_kotlin.viewmodel.Articles_ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class UserList_Activity : AppCompatActivity() {

    private var viewModel : Articles_ViewModel? = null
    private var page : Int? = null
    private var limit: String? = null
    private var adapter : UserList_Adapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list_)

        init()

        id_previous_btn.setOnClickListener {

            page = page?.minus(1)

            if (page == 0){

                page = 1
            }
            getUserDataList(page!!, limit!!)

        }

        id_next_btn.setOnClickListener {

            page = page?.plus(1)
            getUserDataList(page!!, limit!!)

        }
    }

    private fun init() {
        page = 1
        limit = "10"
        id_rc.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProviders.of(this).get<Articles_ViewModel>(Articles_ViewModel::class.java)

        getUserDataList(page!!, limit!!)
    }

    private fun getUserDataList(page_: Int, limit: String) {

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading...")
        progressDialog.show()

        viewModel!!.getUserList(page_, limit).observe(this, object :Observer<List<UserList?>?>{
            override fun onChanged(list: List<UserList?>?) {

                progressDialog.dismiss()

                if (list!!.size == 0){
                    id_rc.visibility = View.GONE
                    id_firstView.visibility = View.GONE
                    page = 0
                }else{
                    id_rc.visibility = View.VISIBLE
                    id_firstView.visibility = View.VISIBLE
                    adapter = UserList_Adapter(applicationContext, list)
                    id_rc!!.adapter = adapter
                }


            }
        })

    }
}