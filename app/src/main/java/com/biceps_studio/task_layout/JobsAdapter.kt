package com.biceps_studio.task_layout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row.view.*

class JobsAdapter : RecyclerView.Adapter<JobsAdapter.ViewHolder>() {

    var list: ArrayList<String> = ArrayList()
    var images: ArrayList<String> = ArrayList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int { return list.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvComapny.text = list[position]

        val options: RequestOptions = RequestOptions().circleCrop().error(R.drawable.ic_error).placeholder(R.drawable.ic_loading)

        Glide.with(holder.itemView).load(images[position]).apply(options).into(holder.itemView.ivCompany)
    }
}