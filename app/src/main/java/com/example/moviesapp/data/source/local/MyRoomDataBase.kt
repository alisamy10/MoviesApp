package com.example.moviesapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.model.homemodel.ResultsItem

/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */


@Database(entities = arrayOf(ResultsItem::class), exportSchema = false,version = 1)
abstract class MyRoomDataBase : RoomDatabase(){
    abstract fun moviesDao():MoviesDao
    companion object{
        private var myDataBaseInstance:MyRoomDataBase?=null

       fun init(context: Context){
            if (myDataBaseInstance==null){
                myDataBaseInstance =
                    Room.databaseBuilder(context,
                        MyRoomDataBase::class.java,"MoviesDataBase")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }

        }
        fun getInstance(): MyRoomDataBase? {
            return myDataBaseInstance
        }
    }
}