package com.example.movieapp.data.model.homemodel

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */
@Entity
data class ResultsItem  (


	@field:SerializedName("overview")
	var overview: String?,


	@field:SerializedName("original_language")
	val originalLanguage: String? = null,


	@field:SerializedName("original_title")
	val originalTitle: String? = null,

	@field:SerializedName("video")
	val video: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@NonNull
	@PrimaryKey(autoGenerate = true)
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("adult")
	val adult: Boolean? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null
) :Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Double::class.java.classLoader) as? Double,
		parcel.readValue(Double::class.java.classLoader) as? Double,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
		parcel.readValue(Int::class.java.classLoader) as? Int
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(overview)
		parcel.writeString(originalLanguage)
		parcel.writeString(originalTitle)
		parcel.writeValue(video)
		parcel.writeString(title)
		parcel.writeString(posterPath)
		parcel.writeString(backdropPath)
		parcel.writeString(releaseDate)
		parcel.writeValue(popularity)
		parcel.writeValue(voteAverage)
		parcel.writeValue(id)
		parcel.writeValue(adult)
		parcel.writeValue(voteCount)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ResultsItem> {
		override fun createFromParcel(parcel: Parcel): ResultsItem {
			return ResultsItem(parcel)
		}

		override fun newArray(size: Int): Array<ResultsItem?> {
			return arrayOfNulls(size)
		}
	}
}