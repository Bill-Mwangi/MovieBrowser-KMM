package com.bill.multiplatformmoviebrowser.movies.data

import com.bill.multiplatformmoviebrowser.movies.domain.Movie
import database.MovieEntity

fun MovieEntity.toMovie(): Movie {
  return Movie(
    id = id,
    imdb_id = imdb_id,
    adult = adult,
    title = title,
    overview = overview,
    runtime = runtime,
    poster_path = poster_path,
    backdrop_path = backdrop_path,
    genre = genre!!,
    status = status!!
  )
}