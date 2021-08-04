package com.geek.android4_5_youtube.ui.playlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geek.android4_5_youtube.R
import com.geek.android4_5_youtube.databinding.ItemPlaylistsRvBinding
import com.geek.android4_5_youtube.model.Item
import com.geek.android4_5_youtube.utils.loadImage

class PlaylistAdapter(
    private val listener: (id: String, title:String)-> Unit
) :
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    private var list = ArrayList<Item>()

    fun updateList(list: ArrayList<Item>) {
        this.list.clear()
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_playlists_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            list[position].let { data -> listener(data.id.toString(), data.snippet?.title.toString()) }
        }
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var ui = ItemPlaylistsRvBinding.bind(itemView)

        fun onBind(item: Item) {
            ui.itemTvTitle.text = item.snippet?.title
            ui.itemTvSub.text = item.contentDetails?.itemCount.toString().plus(" ").plus(itemView.context.getString(R.string.video_series))
            item.snippet?.thumbnails?.medium?.url?.let { ui.itemIv.loadImage(it) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: String,title:String)
    }
}