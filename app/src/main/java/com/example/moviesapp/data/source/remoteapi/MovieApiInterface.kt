package com.example.movieapp.data.source.remoteapi

import com.example.movieapp.data.model.homemodel.ModelHome
import io.reactivex.Single
import retrofit2.http.GET
/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */

interface MovieApiInterface {


    @GET(RetroFitConnection.urlBaseOfAllData+ "movie")
    fun getAll(): Single<ModelHome>

}