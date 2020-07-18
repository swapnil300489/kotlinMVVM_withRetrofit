package com.example.mvvm_kotlin.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_kotlin.R
import com.example.mvvm_kotlin.adapter.ArticlesAdapter
import com.example.mvvm_kotlin.pojo.Articles_
import com.example.mvvm_kotlin.viewmodel.Articles_ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var viewModel : Articles_ViewModel? = null
    private var page : Int? = null
    private var limit: String? = null
    private var adapter : ArticlesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        id_previous_btn.setOnClickListener {

            page = page?.minus(1)

            if (page == 0){

                page = 1
            }
            getArticleslist(page!!, limit!!)

        }

        id_next_btn.setOnClickListener {

            page = page?.plus(1)
            getArticleslist(page!!, limit!!)

        }
    }

    private fun init() {

        page = 1
        limit = "10"
        id_rc.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProviders.of(this).get<Articles_ViewModel>(Articles_ViewModel::class.java)

        getArticleslist(page!!, limit!!)
    }

    private fun getArticleslist(page_: Int, limit: String) {

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading...")
        progressDialog.show()


        viewModel?.getArticles(page_, limit)?.observe(
            this,
            object: Observer<List<Articles_?>?>{

                override fun onChanged(list: List<Articles_?>?) {
                    progressDialog.dismiss()

                    if (list!!.size == 0){
                            id_rc.visibility = View.GONE
                            id_firstView.visibility = View.GONE
                            page = 0
                        }else{
                            id_rc.visibility = View.VISIBLE
                            id_firstView.visibility = View.VISIBLE
                            adapter = ArticlesAdapter(applicationContext, list)
                            id_rc!!.adapter = adapter
                        }

                }
            })
    }
}

