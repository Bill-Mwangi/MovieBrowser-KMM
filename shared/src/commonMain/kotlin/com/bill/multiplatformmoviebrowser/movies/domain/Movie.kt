package com.bill.multiplatformmoviebrowser.movies.domain

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
  val id: Long?,
  val imdb_id: Long?,
  val adult: Boolean,
  val genre: Genre,
  val title: String,
  val overview: String?,
  val runtime: Long?,
  val poster_path: String?,
  val backdrop_path: String?,
  val status: Status
)
