package com.example.movieapp.ui.detailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movieapp.data.model.homemodel.ResultsItem
import com.example.movieapp.data.source.remoteapi.RetroFitConnection
import com.example.movieapp.ext.loadImage
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentDetailsBinding
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */



class DetailsFragment : Fragment() {



    lateinit var resultsItem : ResultsItem
    lateinit var binding : FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)


        resultsItem = DetailsFragmentArgs.fromBundle(arguments!!).item


        Toast.makeText(activity ,resultsItem.posterPath,Toast.LENGTH_LONG).show()

        binding.detailsPosterImage.loadImage(RetroFitConnection.POSTER_BASE_URL+resultsItem.posterPath)

        binding.data=resultsItem
        binding.lifecycleOwner=this

        return binding.root
    }

}
