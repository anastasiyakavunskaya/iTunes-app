package com.example.itunesapp.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunesapp.R
import com.example.itunesapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


//Fragment fo detailed information
class DetailFragment : Fragment()  {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container,false)
        //Collection Id
        val id = DetailFragmentArgs.fromBundle(requireArguments()).collectionId

        binding.lifecycleOwner = this
        val viewModel = ViewModelProviders.of(this)
            .get(DetailViewModel::class.java)
        viewModel.getSongs(id)

        val adapter = SongsListAdapter()
        binding.songsRecycler.adapter = adapter
        val manager = LinearLayoutManager(activity)
        binding.songsRecycler.layoutManager = manager

        viewModel.songs.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)

            //information about collection
            val album = it[0]
            Picasso.get()
                .load(album.artworkUrl100)
                .into(binding.albumImage)
            binding.albumTitle.text = album.collectionName
        })

        return binding.root
    }
}