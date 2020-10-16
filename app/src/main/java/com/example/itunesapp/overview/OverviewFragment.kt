package com.example.itunesapp.overview

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunesapp.*
import com.example.itunesapp.databinding.FragmentOverviewBinding


//fragment to overview all albums
class OverviewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentOverviewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_overview, container, false)

        binding.lifecycleOwner = this
        val viewModel = ViewModelProviders.of(this)
            .get(OverviewViewModel::class.java)

        val adapter =
            AlbumsListAdapter(AlbumListener {
                this.findNavController().navigate(
                    OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(
                        it.collectionId
                    )
                )
            })
        val manager = LinearLayoutManager(activity)
        binding.albumsRecycler.layoutManager = manager
        binding.albumsRecycler.adapter = adapter
        viewModel.albums.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.searchButton.setOnClickListener {
            val imm: InputMethodManager = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().windowToken, 0)
            val term = binding.term.text.toString()
            viewModel.getAlbums(term)
        }
        return binding.root
    }


}