package com.jetpackcompose.book_keeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.jetpackcompose.book_keeper.repository.Repository
import com.jetpackcompose.book_keeper.room.BooksDB
import com.jetpackcompose.book_keeper.room.BooksEntity
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
                    val booksDp= BooksDB.getInstance(ctx)
                    val repo= Repository(booksDB = booksDp)
                    val viewModel=BooksViewModel(repo)
                    HomeScreen(viewModel =viewModel )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: BooksViewModel) {
    var typedInput by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {

        Text(
            text = "Insert Books in Room DataBase",
            modifier = Modifier.padding(vertical = 16.dp),
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 20.sp
        )

        OutlinedTextField(
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
        }) {
            Text(text = "Insert Book into the DB")
        }

        Box(modifier = Modifier.align(Alignment.Start)) {
            Text(
                text = "My Library: ",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Red,
                fontSize = 18.sp
            )


        }


    }

}
