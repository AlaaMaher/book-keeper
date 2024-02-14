package com.jetpackcompose.book_keeper.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDAO {

    @Insert
    suspend fun addBook(booksEntity: BooksEntity)

    @Query("SELECT * FROM BooksEntity")
    fun getAllBooks(): Flow<List<BooksEntity>>

    @Delete
    suspend fun deleteBook(bookEntity: BooksEntity)

    @Update
    suspend fun updateBook(booksEntity: BooksEntity)
}