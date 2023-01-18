package com.bill.multiplatformmoviebrowser.movies.data

import com.bill.multiplatformmoviebrowser.movies.domain.Genre
import com.bill.multiplatformmoviebrowser.movies.domain.Status
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

object DatabaseAdapters {
  val statusEnumColumnAdapter = object : ColumnAdapter<Status, String> {
    override fun decode(databaseValue: String): Status {
      return if (databaseValue.isNotBlank()) {
        Status.valueOf(databaseValue)
      } else {
        Status.valueOf("null")
      }
    }

    override fun encode(value: Status): String {
      return value.name
    }
  }

  val genreListColumnAdapter = object : ColumnAdapter<List<Genre>, String> {
    override fun decode(databaseValue: String): List<Genre> {
      return if (databaseValue.isNotEmpty()) {
        Json.decodeFromString(ListSerializer(Genre.serializer()), databaseValue)
      } else {
        listOf()
      }
    }

    override fun encode(value: List<Genre>): String {
      return Json.encodeToString(ListSerializer(Genre.serializer()), value)
    }
  }
}