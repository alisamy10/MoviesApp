package com.example.movieapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.model.homemodel.ModelHome
import com.example.movieapp.data.model.homemodel.ResultsItem
import com.example.movieapp.data.repo.Repoistory
import com.example.moviesapp.data.source.local.MyRoomDataBase
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */



class HomeViewModel  : ViewModel() {
    var data  = MutableLiveData<ModelHome>()
    var check = MutableLiveData<String>()
    var repositryMovieDetails: Repoistory = Repoistory
    private val disposables = CompositeDisposable()

    fun ViewModelOfDetails() {
        data = repositryMovieDetails.data
        check=repositryMovieDetails.msg
        repositryMovieDetails.loadMovies()
    }

    fun getDisposables(): CompositeDisposable? {
        return disposables
    }

    fun getAllMovies(): Flowable<List<ResultsItem>>? {
        return MyRoomDataBase.getInstance()?.moviesDao()?.getAllMovies()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError({ throwable ->
                Log.e(
                    "a",
                    "getAllNotes: Error retrieving data"
                )
            })
    }


    fun insertMovie(item: ResultsItem?) {

        if (item != null) {
            MyRoomDataBase.getInstance()?.moviesDao()?.insert(item)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnComplete({ Log.d("a", "updateNote: updated successfully") })
                ?.doOnError({ i -> Log.e("a", "updateNote: " + i.message) })
                ?.subscribe()?.let {
                    disposables.add(
                        it
                    )
                }
        }

    }

    fun deleteMovie(item: ResultsItem?) {

        if (item != null) {
            MyRoomDataBase.getInstance()?.moviesDao()?.delete(item)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnComplete({ Log.d("a", ": Deleted  successfully") })
                ?.doOnError({ i -> Log.e("a", i.message) })
                ?.subscribe()?.let {
                    disposables.add(
                        it
                    )
                }
        }

    }


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}