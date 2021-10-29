package com.example.android_seminar_week3

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_seminar_week3.databinding.ItemFollowerBinding

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>(){
    val repositoryList = mutableListOf<RepositoryData>()

    class RepositoryViewHolder(private val binding: ItemFollowerBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : RepositoryData){
            binding.ivProfile.visibility = GONE
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(repositoryList[position])
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }
}