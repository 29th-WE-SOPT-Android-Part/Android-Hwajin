package com.example.android_seminar_week4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_seminar_week4.databinding.ItemFollowerBinding
import com.bumptech.glide.Glide

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(){
    val followerList = mutableListOf<FollowerData>()

    class FollowerViewHolder(private val binding: ItemFollowerBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : FollowerData){
            Glide.with(binding.root).load(data.img).circleCrop().into(binding.ivProfile)
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.introduction
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int {
        return followerList.size
    }

}