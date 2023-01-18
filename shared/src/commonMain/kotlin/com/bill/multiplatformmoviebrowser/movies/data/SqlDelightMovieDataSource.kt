package com.bill.multiplatformmoviebrowser.movies.data

import com.bill.multiplatformmoviebrowser.database.MovieDatabase
import com.bill.multiplatformmoviebrowser.movies.domain.Movie
import com.bill.multiplatformmoviebrowser.movies.domain.MovieDataSource

class SqlDelightMovieDataSource(db: MovieDatabase) : MovieDataSource {
  private val queries = db.movieQueries

  override suspend fun getAllMovies(): List<Movie> {
    return queries.getAllMovies().executeAsList().map { it.toMovie() }
  }

  override suspend fun getMovieById(id: Long): Movie? {
    return queries.getMovieById(id).executeAsOneOrNull()?.toMovie()
  }

  override suspend fun insertMovie(movie: Movie) {
    queries.insertMovie(
      id = movie.id,
      imdb_id = movie.imdb_id,
      adult = movie.adult,
      genre = movie.genre,
      title = movie.title,
      overview = movie.overview,
      poster_path = movie.poster_path,
      runtime = movie.runtime,
      backdrop_path = movie.backdrop_path
    )
  }
}