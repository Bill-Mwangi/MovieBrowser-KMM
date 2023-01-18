package com.bill.multiplatformmoviebrowser.movies.domain

interface MovieDataSource {
  suspend fun getAllMovies(): List<Movie>
  suspend fun getMovieById(id: Long): Movie?
  suspend fun insertMovie(movie: Movie)
}