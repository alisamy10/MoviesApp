package com.example.movieapp.ui.favouritefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.model.homemodel.ResultsItem
import com.example.movieapp.ui.HomeViewModel
import com.example.movieapp.ui.adapter.HomeRecycleAdapter
import com.example.movieapp.ui.homefragment.HomeFragmentDirections
import com.example.moviesapp.R
import com.example.moviesapp.data.source.local.MyRoomDataBase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_favourite.*


/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */



class FavouriteFragment : Fragment(), HomeRecycleAdapter.OnItemClicked {

    lateinit var adapter: HomeRecycleAdapter
    lateinit var recycler_view: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favourite, container, false)
        val viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        recycler_view = view.findViewById(R.id.fav_rec)

        initRecycler()


        viewModel.getAllMovies()?.doOnNext(Consumer {
            adapter.submitList(it)
        })?.subscribe()?.let { viewModel.getDisposables()?.add(it) }


        return view
    }

    private fun initRecycler() {
        adapter = HomeRecycleAdapter(this)
        recycler_view.adapter = adapter
    }


    override fun onPosterSelected(position: Int, item: ResultsItem) {

    }

    override fun onAddedToFavSelected(position: Int, item: ResultsItem) {
        Log.e("a", "a")
    }


}



