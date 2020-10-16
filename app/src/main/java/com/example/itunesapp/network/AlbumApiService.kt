package com.example.itunesapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://itunes.apple.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AlbumsApiService {

    // search for album by album name or artist name
    @GET("search?")
    suspend fun getAlbums(@Query("term") term: String,
                          @Query("entity") entity:String): Response

    //lookup information about selected album and it's songs
    @GET("lookup?")
    suspend fun getSongs(@Query("id") id:Int,
                         @Query("entity") entity: String): Songs
}

object AlbumsApi {
    val retrofitService : AlbumsApiService by lazy { retrofit.create(AlbumsApiService::class.java) }
}