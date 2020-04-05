package com.example.moviesapp.data.source.local

import androidx.room.*
import com.example.movieapp.data.model.homemodel.ResultsItem
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */


@Dao
interface MoviesDao {


    //using completable observer to alert me as soon as the insertion operation is completed
    @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insert(item : ResultsItem ):Completable



      @Query("select * from ResultsItem")
      fun getAllMovies():Flowable<List<ResultsItem>>




      @Delete
      fun delete(item :ResultsItem):Completable
}