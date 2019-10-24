package com.biceps_studio.task_layout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biceps_studio.task_layout.R
import com.biceps_studio.task_layout.data.PostModel
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var arrayList: ArrayList<PostModel> = ArrayList()

    fun updateData(arrayList: ArrayList<PostModel>){
        this.arrayList = arrayList

        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int { return arrayList.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postModel: PostModel = arrayList[position]

        holder.itemView.tvTitle.text = postModel.title
        holder.itemView.tvContent.text = postModel.body
    }

}