package com.example.mytask.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytask.Model.Response.PostResponse
import com.example.mytask.databinding.CustomListLayoutBinding

class PostAdapter : RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private lateinit var binding: CustomListLayoutBinding
    private var data: List<PostResponse>
    private var mContext: Context
    private val listener :OnClickListener

    constructor(data: List<PostResponse>, mContext: Context,listener : OnClickListener) {
        this.data = data
        this.mContext = mContext
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        binding = CustomListLayoutBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        binding.txtId.text = "id  : " + data[position].id
        binding.txtUserid.text = "UserId  : " + data[position].userId
        binding.txtTitle.text = "Title  : " + data[position].title


        holder.itemView.setOnClickListener {
            listener.OnItemClickListener(data,position)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


    interface OnClickListener {
        fun OnItemClickListener(data: List<PostResponse>, position: Int)
    }
}