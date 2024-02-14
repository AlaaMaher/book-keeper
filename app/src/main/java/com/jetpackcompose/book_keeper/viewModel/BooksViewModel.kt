package com.jetpackcompose.book_keeper.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcompose.book_keeper.repository.Repository
import com.jetpackcompose.book_keeper.room.BooksEntity
import kotlinx.coroutines.launch

class BooksViewModel(val repository: Repository) : ViewModel() {

    fun addBook(booksEntity: BooksEntity) {
        viewModelScope.launch {
            repository.addBook(booksEntity)
        }
    }

    val books = repository.getAllBooks()

    fun deleteBook(booksEntity: BooksEntity) {
        viewModelScope.launch { repository.deleteBook(booksEntity) }
    }

    fun updateBook(booksEntity: BooksEntity){
        viewModelScope.launch {
            repository.updateBook(booksEntity)
        }
    }
}