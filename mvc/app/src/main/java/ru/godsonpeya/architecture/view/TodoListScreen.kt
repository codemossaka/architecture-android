package ru.godsonpeya.architecture.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.godsonpeya.architecture.model.Todo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun TodoListScreen(
    todos: List<Todo>,
    onAddTodo: (String) -> Unit,
    onDeleteTodo: (Todo) -> Unit,
    onBackup: () -> Unit,
    onRestore: () -> Unit
) {
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
            onAddTodo.invoke(newTodoText)
            newTodoText = ""
        }) {
            Text("Add Todo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Boutons de backup et restauration
        Row {
            Button(onClick = onBackup) {
                Text("Backup Todos")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = onRestore) {
                Text("Restore Todos")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of todos
        LazyColumn {
            items(todos) { todo ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(todo.title, modifier = Modifier.weight(1f))
                    IconButton(onClick = { onDeleteTodo.invoke(todo) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Todo")
                    }
                }
            }
        }
    }
}

