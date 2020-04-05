package com.example.movieapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.data.model.homemodel.ResultsItem
import com.example.movieapp.data.source.remoteapi.RetroFitConnection
import com.example.moviesapp.R
import kotlinx.android.synthetic.main.home_list_item.view.*

/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */



class HomeRecycleAdapter(private val interaction: OnItemClicked? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultsItem>() {

        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id==oldItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return  oldItem==newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_list_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ResultsItem>?) {
        differ?.submitList(list)
    }

    class HomeViewHolder
    constructor(
        itemView: View,
        private val onClicked: OnItemClicked?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ResultsItem) = with (itemView) {

            itemView.add_to_fav.setOnClickListener({
                onClicked?.onAddedToFavSelected(adapterPosition, item)
            })

            itemView.item_image.setOnClickListener({
                onClicked?.onPosterSelected(adapterPosition, item)
            })

            val requestOptions = RequestOptions
                .overrideOf(1920, 1080)
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(RetroFitConnection.POSTER_BASE_URL+item.posterPath)
                .into(itemView.item_image)

            itemView.item_tile.text = item.title

        }
    }

    interface OnItemClicked {
        fun onPosterSelected(position: Int, item: ResultsItem)

        fun onAddedToFavSelected(position: Int, item: ResultsItem)


    }
}





