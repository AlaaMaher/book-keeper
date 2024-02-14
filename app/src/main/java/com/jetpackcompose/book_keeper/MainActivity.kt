package com.jetpackcompose.book_keeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetpackcompose.book_keeper.repository.Repository
import com.jetpackcompose.book_keeper.room.BooksDB
import com.jetpackcompose.book_keeper.room.BooksEntity
import com.jetpackcompose.book_keeper.screens.UpdateScreen
import com.jetpackcompose.book_keeper.ui.theme.BookkeeperTheme
import com.jetpackcompose.book_keeper.viewModel.BooksViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookkeeperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val ctx = LocalContext.current
                    val booksDp = BooksDB.getInstance(ctx)
                    val repo = Repository(booksDB = booksDp)
                    val viewModel = BooksViewModel(repo)


                    //Navigation

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "HomeScreen"){
                        composable("HomeScreen"){
                            HomeScreen(viewModel = viewModel,navController)

                        }
                        composable("UpdateScreen/{bookId}"){
                            UpdateScreen(viewModel = viewModel, bookId =it.arguments?.getString("bookId") )
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: BooksViewModel, navController: NavHostController) {
    var typedInput by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 22.dp, start = 6.dp, end = 6.dp)
    ) {

        Text(
            text = "Insert Books in ROOM DB",
            modifier = Modifier.padding(vertical = 16.dp),
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 22.sp
        )

        OutlinedTextField(
            modifier = Modifier.padding(bottom = 6.dp),
            value = typedInput,
            onValueChange = { value -> typedInput = value },
            singleLine = true,
            placeholder = { Text(text = "Book Name") },
            label = {
                Text(
                    text = "Book Name", fontSize = 18.sp, color = Color.Gray
                )
            })

        Button(onClick = {
            viewModel.addBook(BooksEntity(0, typedInput))
        }, colors = ButtonDefaults.buttonColors(Color.Blue)) {
            Text(text = "Insert Book into the DB")
        }

        Column(modifier = Modifier.align(Alignment.Start).padding(16.dp)) {
            Text(
                text = "My Library: ",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Red,
                fontSize = 18.sp
            )
            
            BooksList(viewModel = viewModel,navController)


        }


    }

}

@Composable
fun BookCard(viewModel: BooksViewModel, book: BooksEntity, navHostController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Row (verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "" + book.id,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                color = Color.Blue
            )
            Text(text = book.title,
                modifier = Modifier.fillMaxSize(0.7f),
                color = Color.Black,
                fontSize = 24.sp)
            Row (horizontalArrangement = Arrangement.End){
                IconButton(onClick = {viewModel.deleteBook(booksEntity = book)}) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
                IconButton(onClick = {
                    navHostController.navigate("UpdateScreen/${book.id}")
                }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Update")
                }

            }

        }

    }
}

@Composable
fun BooksList(viewModel: BooksViewModel, navHostController: NavHostController){
    val  books by viewModel.books.collectAsState(initial = emptyList())

    LazyColumn{
     items(books){
         item -> BookCard(viewModel = viewModel, book = item, navHostController)
     }
    }
}
