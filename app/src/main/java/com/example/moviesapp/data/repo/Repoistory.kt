package com.example.movieapp.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.data.DataSourceState
import com.example.movieapp.data.model.homemodel.ModelHome
import com.example.movieapp.data.source.remoteapi.RetroFitConnection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */



object Repoistory   {

    var data   = MutableLiveData<ModelHome>()

    val msg    =  MutableLiveData<String>()

    private val movies = mutableListOf<ModelHome>()


    // val networkState  = MutableLiveData<DataSourceState>()
    /*

    private val TAG = UserRepository::class.java.simpleName
    private val db = FirebaseFirestore.getInstance()
    private val usersCollation = db.collection("user")

     */


    private var lastUpdate: Int = 1

    fun loadMovies() {
        if (lastUpdate > 5)
        /*If you fetched the data from memory twice you should
        * load it from the remote source next time to keep it updated*/
            //getUsersFromRemoteSource()
        getMovieDetails()
        else
            if (getUsersFromMemory()== DataSourceState.MEMORY) {
                //return users here
            } else if (getUsersFormCashedDb()) {
                //return users here
            } else {
                //getUsersFromRemoteSource()
                Log.e("a", "from api ")
                getMovieDetails()

            }
    }


    /*fun addUser() {
        val user2 = User(25, "Mohamed", 20)
        usersCollation.document().set(user2)
    }

     */



    private fun getUsersFromMemory(): DataSourceState {
        if (movies.size == 0)
            return DataSourceState.REMOTE
        else {
            Log.e("a", "From memory data source")
            lastUpdate++
            return DataSourceState.MEMORY
        }
    }


    /**
     * Function used to simulate the database source,
     * @return false, that mean the database didn't have the required data,
     * So, you should fetch it from network.
     * */
    private fun getUsersFormCashedDb(): Boolean {
        return false
    }

    /*private fun getUsersFromRemoteSource() {
        usersCollation.get()
            .addOnSuccessListener {
                usersList.clear()
                Log.d(TAG, "From remote data source")
                for (document: DocumentSnapshot in it.documents) {
                    val user = document.toObject(User::class.java)
                    usersList.add(user!!)
                }
                displayUsers(usersList)
            }
            .addOnFailureListener { ex ->
                Log.e(TAG, "Can't load users documents ${ex.message}")
            }
    }

     */


    fun getMovieDetails () : Disposable? {

        return RetroFitConnection.getClient().getAll().subscribeOn(Schedulers.io()).
        observeOn(AndroidSchedulers.mainThread()).subscribe({
            data.value=it
            movies.add(it)

        },{
            msg.value=it.localizedMessage
        })


    }


}