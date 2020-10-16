package com.example.itunesapp.network

//responses to HTTP methods

data class Response(
    val resultCount: Int,
    val results: List<Album>
)

data class Album (
    val artistId: Int,
    val collectionId: Int,
    val artistName:String,
    val collectionName: String,
    val artworkUrl100 :String,
    val trackCount: Int,
    val copyright: String = "unknown",
    val country: String = "unknown",
    val currency: String = "unknown",
    val releaseDate: String = "unknown",
    val primaryGenreName: String = "unknown"
)

 data class Songs(
     val resultCount: Int,
     val results: List<Song>
 )

data class Song(
    val artistName: String,
    val collectionName: String,
    val collectionCensoredName: String,
    val trackName: String = "unknown",
    val artistViewUrl: String,
    val collectionViewUrl: String,
    val releaseDate: String = "unknown",
    val artworkUrl100: String
)
