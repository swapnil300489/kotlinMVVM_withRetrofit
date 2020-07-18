package com.example.mvvm_kotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_kotlin.R
import com.example.mvvm_kotlin.pojo.UserList
import com.example.mvvm_kotlin.ui.UserProfile
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserList_Adapter(
    var applicationContext: Context,
    var list: List<UserList?>
) : RecyclerView.Adapter<UserList_Adapter.UserList_ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserList_ViewHolder {
        return UserList_ViewHolder(
            LayoutInflater.from(applicationContext).inflate(R.layout.userlist_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserList_ViewHolder, position: Int) {
        Picasso.get().load(list[position]!!.avatar).into(holder.id_userPro)

        val userName: String? = list[position]!!.name +""+list[position]!!.lastname

        holder.id_userName_txt.text = userName
        holder.id_city_txt.text = list[position]!!.city
        holder.id_userdesignation_txt.text = list[position]!!.designation


        holder.id_userPro.setOnClickListener {

            userProfileScreen(list[position])

        }

    }

    private fun userProfileScreen(user: UserList?) {

        val intent = Intent(this.applicationContext, UserProfile::class.java)
        intent.putExtra("profile_url", user!!.avatar)
        intent.putExtra("name",user.name+" "+user.lastname)
        intent.putExtra("designation", user.designation)
        intent.putExtra("City", user.city)
        intent.putExtra("bio",user.about)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
        applicationContext.startActivity(intent)
    }


    class UserList_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var id_userPro : CircleImageView = itemView.findViewById(R.id.id_userPro)
        var id_userName_txt : TextView = itemView.findViewById(R.id.id_userName_txt)
        var id_city_txt : TextView = itemView.findViewById(R.id.id_city_txt)
        var id_userdesignation_txt : TextView = itemView.findViewById(R.id.id_userdesignation_txt)


    }
}