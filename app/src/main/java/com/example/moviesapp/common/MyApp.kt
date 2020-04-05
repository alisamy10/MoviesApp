package com.example.moviesapp.common

import android.app.Application
import com.example.moviesapp.data.source.local.MyRoomDataBase


/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */

class MyApp :Application() {


    override fun onCreate() {
        super.onCreate()
        MyRoomDataBase.init(this)
    }
}