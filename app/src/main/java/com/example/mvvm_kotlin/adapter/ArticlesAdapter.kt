package com.example.mvvm_kotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_kotlin.R
import com.example.mvvm_kotlin.pojo.Articles_
import com.example.mvvm_kotlin.ui.UserProfile
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.ln
import kotlin.math.pow

class ArticlesAdapter(
    val applicationContext: Context,
    val list: List<Articles_?>?
) : RecyclerView.Adapter<ArticlesAdapter.Articles_ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Articles_ViewHolder {
            return Articles_ViewHolder(LayoutInflater.from(applicationContext).inflate(R.layout.articles_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Articles_ViewHolder, position: Int) {


        Picasso.get().load(list!![position]?.user!![0].avatar).into(holder.id_userPro)

        val userName  = list[position]?.user!![0].name +" "+ list[0]?.user!![0].lastname
        val userdesig = list[position]?.user!![0].designation

        holder.id_userName_txt.text = userName
        holder.id_userdesignation_txt.text = userdesig



        if(list[position]?.media?.size == 0){

            holder.id_title_txt.visibility = View.GONE
            holder.id_article_url_txt.visibility = View.GONE
            holder.id_mediaImg.visibility = View.GONE

        }else{

            val title     = list[position]?.media!![0].title
            val url       = list[position]?.media!![0].url
            holder.id_title_txt.text = title
            holder.id_article_url_txt.text = url
            Picasso.get().load(this.list[position]?.media!![0].image).into(holder.id_mediaImg)
        }


        holder.id_content_txt.text = this.list[position]?.content

        val like    = getFormatedNumber(list[position]?.likes)
        val comment = getFormatedNumber(list[position]?.comments)

        holder.id_likes_countText.text = "$like Likes"
        holder.id_likes_commentText.text = "$comment Comment"


        holder.id_userPro.setOnClickListener {

            userProfileScreen(list!![position]?.user!![0])

        }



    }

    private fun userProfileScreen(user: Articles_.User) {

        val intent = Intent(this.applicationContext, UserProfile::class.java)
        intent.putExtra("profile_url", user.avatar)
        intent.putExtra("name",user.name+" "+user.lastname)
        intent.putExtra("designation", user.designation)
        intent.putExtra("City", user.city)
        intent.putExtra("bio",user.about)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
        applicationContext.startActivity(intent)

    }

    fun getFormatedNumber(count: Int?): String {
        if (count!! < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
    }


    class Articles_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id_userPro: CircleImageView         = itemView.findViewById(R.id.id_userPro)
        val id_userName_txt: TextView           = itemView.findViewById(R.id.id_userName_txt)
        val id_hours_txt: TextView              = itemView.findViewById(R.id.id_hours_txt)
        val id_userdesignation_txt: TextView    = itemView.findViewById(R.id.id_userdesignation_txt)
        val id_mediaImg: ImageView              = itemView.findViewById(R.id.id_mediaImg)
        val id_content_txt : TextView           = itemView.findViewById(R.id.id_content_txt);
        val id_title_txt: TextView              = itemView.findViewById(R.id.id_title_txt)
        val id_article_url_txt: TextView        = itemView.findViewById(R.id.id_article_url_txt)
        val id_likes_countText: TextView        = itemView.findViewById(R.id.id_likes_countText)
        val id_likes_commentText: TextView      = itemView.findViewById(R.id.id_likes_commentText)
    }
}