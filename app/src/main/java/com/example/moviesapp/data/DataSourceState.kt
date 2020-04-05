package com.example.movieapp.data

/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */


enum class Status{
    memory  ,
    locale  ,
    remote


}
class DataSourceState (val status: Status) {

    companion object {

        val MEMORY: DataSourceState
        val LOCALE: DataSourceState
        val REMOTE: DataSourceState

        init {
            MEMORY = DataSourceState(Status.memory)

            LOCALE = DataSourceState(Status.locale)

            REMOTE = DataSourceState(Status.remote)

        }
    }
}