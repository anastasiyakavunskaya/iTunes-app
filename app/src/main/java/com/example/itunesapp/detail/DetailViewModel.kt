package com.example.itunesapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesapp.network.AlbumsApi
import com.example.itunesapp.network.Song
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel(){

    private var _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> = _songs

    fun getSongs(id: Int) {
        viewModelScope.launch {
            try {
                val entity = "song"
                val result = AlbumsApi.retrofitService.getSongs(id, entity).results
                val size = result.size
                val i = result.subList(1, size)
                _songs.value = i
            } catch (e: Exception) {
                _songs.value = ArrayList()
            }
        }
    }
}