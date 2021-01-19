package com.bhavinpracticalinfosys.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhavinpracticalinfosys.R
import com.bhavinpracticalinfosys.model.Feed
import com.bhavinpracticalinfosys.utils.GlideApp
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.raw_feed.view.*
import javax.inject.Inject


class FeedListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var feeds: List<Feed.Row?>? = null

    fun setData(feeds: List<Feed.Row?>?) {
        this.feeds = feeds
        notifyDataSetChanged()
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.raw_feed, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (feeds != null) feeds!!.size else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        feeds?.get(position)?.apply {
            holder.itemView.title.text = if(title.isNullOrEmpty()) "--" else title
            holder.itemView.desc.text = if(description.isNullOrEmpty()) "--" else description
            GlideApp.with(holder.itemView.image.context)
                .load(imageHref?.trim())
                .placeholder(R.color.gray)
                .error(R.color.gray)
                .into(holder.itemView.image)
        }
    }
}