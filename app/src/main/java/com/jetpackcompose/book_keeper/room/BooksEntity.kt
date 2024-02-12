package com.jetpackcompose.book_keeper.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BooksEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String
)
