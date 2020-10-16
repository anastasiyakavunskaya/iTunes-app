package com.example.itunesapp.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesapp.databinding.SongItemBinding
import com.example.itunesapp.network.Song


class SongsListAdapter
    : ListAdapter<Song, SongsListAdapter.SongViewHolder> (DiffCallback) {


    class SongViewHolder(private var binding: SongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            val trackName = song.trackName
                binding.songTitle.text = trackName
        }
    }

        companion object DiffCallback : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem.trackName == newItem.trackName
            }
        }

        override fun onCreateViewHolder( parent: ViewGroup,
                                         viewType: Int): SongViewHolder {
            return SongViewHolder(
                SongItemBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
            val song = getItem(position)
            holder.bind(song)
        }
}

