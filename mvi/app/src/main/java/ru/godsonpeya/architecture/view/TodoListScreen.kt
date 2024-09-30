package ru.godsonpeya.architecture.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.godsonpeya.architecture.mvi.TodoIntent
import ru.godsonpeya.architecture.mvi.TodoViewModel
import kotlin.let

@Composable
fun TodoListScreen(
    viewModel: TodoViewModel
) {
    val state by viewModel.state.collectAsState()

    var newTodoTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Input field to add a new todo
        OutlinedTextField(
            value = newTodoTitle,
            onValueChange = { newTodoTitle = it },
            label = { Text("New Todo") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.handleIntent(TodoIntent.AddTodoIntent(newTodoTitle))
            newTodoTitle = ""
        }) {
            Text("Add Todo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Show loading state
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        // Show error message
        state.errorMessage?.let { error ->
            Text(error, color = MaterialTheme.colorScheme.error)
        }

        // List of todos
        LazyColumn {
            items(state.todos) { todo ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(todo.title, modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        viewModel.handleIntent(TodoIntent.DeleteTodoIntent(todo))
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Todo")
                    }
                }
            }
        }

        // Backup and Restore buttons
        Row {
            Button(onClick = {
                viewModel.handleIntent(TodoIntent.BackupTodosIntent)
            }) {
                Text("Backup Todos")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                viewModel.handleIntent(TodoIntent.RestoreTodosIntent)
            }) {
                Text("Restore Todos")
            }
        }
    }

    // Load todos on first launch
    LaunchedEffect(Unit) {
        viewModel.handleIntent(TodoIntent.LoadTodosIntent)
    }
}
