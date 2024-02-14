package com.jetpackcompose.book_keeper.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpackcompose.book_keeper.room.BooksEntity
import com.jetpackcompose.book_keeper.viewModel.BooksViewModel

@Composable
fun UpdateScreen(viewModel: BooksViewModel, bookId:String?){
    var newValue by remember { mutableStateOf("") }
    Column (modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Update The Book Title With a new one", fontSize = 24.sp)
        OutlinedTextField(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp), value =newValue , onValueChange = {value -> newValue = value }
        , label = { Text(text = "Update Book Name")}, placeholder = { Text(text = "New Book Name")})

        Button(colors = ButtonDefaults.buttonColors(Color.Red), onClick = {
            val newBook = BooksEntity(bookId!!.toInt(),newValue)
            viewModel.updateBook(newBook)}) {
            
            Text(text = "Update Book")
            
        }
    }
}