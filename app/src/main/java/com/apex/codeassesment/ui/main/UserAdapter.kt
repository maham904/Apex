package com.apex.codeassesment.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apex.codeassesment.R
import com.apex.codeassesment.data.model.Response.Info
import com.apex.codeassesment.data.model.Response.Results
import com.apex.codeassesment.data.model.Response.UserResponse
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.databinding.ItemViewBinding
import com.bumptech.glide.Glide

class UserAdapter(private val userList: List<User>, private val context: Context, private val onItemViewClickListener: OnItemViewClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    class ViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root){
        val email : TextView = binding.mainEmail
        val name : TextView = binding.mainName
        val img : ImageView = binding.mainImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(view,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val item = userList[position]
        holder.name.text = item.name?.first
        holder.email.text = item.email
        val imageUrl = item.picture?.large
        Glide.with(context).load(imageUrl).into(holder.img)
        holder.itemView.setOnClickListener { onItemViewClickListener.onItemClick(item) }

    }

    override fun getItemCount(): Int {
      return  userList.size
    }

    interface OnItemViewClickListener{
        fun onItemClick(user: User)
    }
}