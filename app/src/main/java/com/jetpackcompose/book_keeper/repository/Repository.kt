package com.jetpackcompose.book_keeper.repository

import com.jetpackcompose.book_keeper.room.BooksDB
import com.jetpackcompose.book_keeper.room.BooksEntity

class Repository(val booksDB: BooksDB) {

    suspend fun addBook(booksEntity: BooksEntity) {
        booksDB.booksDAO().addBook(booksEntity)
    }

    fun getAllBooks() = booksDB.booksDAO().getAllBooks()

    suspend fun deleteBook(booksEntity: BooksEntity){
        booksDB.booksDAO().deleteBook(booksEntity)
    }

    suspend fun updateBook(booksEntity: BooksEntity){
        booksDB.booksDAO().updateBook(booksEntity)
    }
}