package com.bill.multiplatformmoviebrowser.movies.domain

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
  val id: Long,
  val name: String
)
