package com.example.itunesapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesapp.network.Album
import com.example.itunesapp.network.AlbumsApi
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private var _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    fun getAlbums(term: String) {
        viewModelScope.launch {
            try {
                val entity = "album"
                val result = AlbumsApi.retrofitService.getAlbums(term, entity).results.sortedBy { it.collectionName }
                _albums.value = result
            } catch (e: Exception) {
                _albums.value = ArrayList()
            }
        }
    }

}