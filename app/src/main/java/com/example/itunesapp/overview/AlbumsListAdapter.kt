package com.example.itunesapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesapp.databinding.AlbumItemBinding
import com.example.itunesapp.network.Album
import com.squareup.picasso.Picasso


class AlbumsListAdapter(private val clickListener: AlbumListener)
    : ListAdapter<Album, AlbumsListAdapter.AlbumsViewHolder>(DiffCallback) {

    class AlbumsViewHolder(private var binding: AlbumItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album, clickListener: AlbumListener) {
            val url = album.artworkUrl100
            Picasso.get()
                .load(url)
                .into(binding.albumImg)

            binding.album = album
            binding.clickListener = clickListener
            binding.release.text = convertDate(album.releaseDate)
            binding.executePendingBindings()
        }

        // convert date to DD.MM.YYYY format
        private fun convertDate(releaseDate: String): String {
            val date =  releaseDate.split("T")[0]
            val partition = date.split("-")
            return partition[2]+"."+partition[1]+"."+partition[0]
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AlbumsViewHolder {
        return AlbumsViewHolder(
            AlbumItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album, clickListener)
    }
}

class AlbumListener (val clickListener: (album: Album) -> Unit) {
        fun onClick(album: Album) = clickListener(album)
}
