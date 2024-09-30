package ru.godsonpeya.architecture.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.godsonpeya.architecture.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(
    viewModel: TodoViewModel
) {
    val todos by viewModel.todos.collectAsState()

    var newTodoText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Input field to add a new todo
        OutlinedTextField(
            value = newTodoText,
            onValueChange = { newTodoText = it },
            label = { Text("New Todo") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.addTodo(newTodoText)
            newTodoText = ""
        }) {
            Text("Add Todo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Boutons de backup et restauration
        Row {
            Button(onClick = { viewModel.backupTodos { /* handle result */ } }) {
                Text("Backup Todos")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { viewModel.restoreTodos { /* handle result */ } }) {
                Text("Restore Todos")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of todos
        LazyColumn {
            items(todos) { todo ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(todo.title, modifier = Modifier.weight(1f))
                    IconButton(onClick = { viewModel.deleteTodo(todo) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Todo")
                    }
                }
            }
        }
    }
}
