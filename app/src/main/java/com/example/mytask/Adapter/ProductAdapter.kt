package com.example.mytask.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mytask.Model.Response.ProductResponse
import com.example.mytask.databinding.CustomListLayoutBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private lateinit var binding: CustomListLayoutBinding
    private var data: List<ProductResponse>
    private var mContext: Context
    private val listener :OnClickListener

    constructor(data: List<ProductResponse>, mContext: Context, listener : OnClickListener) {
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
        Glide.with(mContext)
            .asBitmap()
            .load(data.get(position).image)
            .thumbnail(0.05f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.image)

        binding.name.text = data[position].title

        binding.price.text = "Rs. "+data[position].price

        holder.itemView.setOnClickListener {
            listener.OnItemClickListener(data,position)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


    interface OnClickListener {
        fun OnItemClickListener(data: List<ProductResponse>, position: Int)
    }
}