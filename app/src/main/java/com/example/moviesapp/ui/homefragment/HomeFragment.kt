package com.example.movieapp.ui.homefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.model.homemodel.ResultsItem
import com.example.movieapp.ui.HomeViewModel
import com.example.movieapp.ui.adapter.HomeRecycleAdapter
import com.example.moviesapp.R
import com.example.moviesapp.data.source.local.MyRoomDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */



class HomeFragment : Fragment() , HomeRecycleAdapter.OnItemClicked {


    lateinit var adapter: HomeRecycleAdapter
    lateinit var recycler_view: RecyclerView
    lateinit var navController: NavController
    var isSaved=true
    lateinit var viewModel :HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.fragment_home, container, false)
         viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        recycler_view = view.findViewById(R.id.recycler_view)


        initRecycler()
        viewModel.ViewModelOfDetails()
        viewModel.data.observe(this, androidx.lifecycle.Observer {
            adapter.submitList(it.results as List<ResultsItem>)


        })

        return view
    }

    override fun onPosterSelected(position: Int, item: ResultsItem) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item)

        navController.navigate(action)
    }




    override fun onAddedToFavSelected(position: Int, item: ResultsItem) {

        Log.e("a","jlj")
        if (isSaved) {
            addToFav(item)
            isSaved = false
        }
        else {
            isSaved = true
            removeFromFav(item)
        }
    }






    private fun addToFav(item: ResultsItem) {
       viewModel.insertMovie(item)
    }




    private fun removeFromFav(item: ResultsItem) {
     viewModel.deleteMovie(item)
    }

    private fun initRecycler() {
        adapter = HomeRecycleAdapter( this)
        recycler_view.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
    }


}


