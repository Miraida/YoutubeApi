package com.geek.android4_5_youtube.ui.detail_playlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geek.android4_5_youtube.R
import com.geek.android4_5_youtube.databinding.ItemPlaylistRvBinding
import com.geek.android4_5_youtube.model.Item
import com.geek.android4_5_youtube.ui.playlist.PlaylistAdapter
import com.geek.android4_5_youtube.utils.loadImage

class DetailPlaylistAdapter: RecyclerView.Adapter<DetailPlaylistAdapter.ViewHolder>() {

    private var list = ArrayList<Item>()
    private lateinit var listener:PlaylistAdapter.OnItemClickListener

    fun updateList(list:ArrayList<Item>){
        this.list.clear()
        this.list = list
        notifyDataSetChanged()
    }
    fun setupListener(listener:PlaylistAdapter.OnItemClickListener){
        this.listener = listener
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var ui = ItemPlaylistRvBinding.bind(itemView)

        fun onBind(item: Item) {
            ui.itemTvTitle.text = item.snippet?.title
            ui.itemIv.loadImage(item.snippet?.thumbnails?.medium?.url.toString())
            ui.itemTvSub.text = item.contentDetails?.itemCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_playlist_rv,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(list[position].id.toString(),list[position].snippet?.title.toString())
        }
    }

    override fun getItemCount(): Int = list.size
}