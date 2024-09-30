package ru.godsonpeya.architecture.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ru.godsonpeya.architecture.domain.model.Todo

@Composable
fun TodoListScreen(
    todos: List<Todo>,
    onAddTodo: (String) -> Unit,
    onDeleteTodo: (Todo) -> Unit,
    onBackup: () -> Unit,
    onRestore: () -> Unit
) {
    val newTodoTitle = remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = newTodoTitle.value,
            onValueChange = { newTodoTitle.value = it },
            label = { Text("New Todo") }
        )

        Button(onClick = {
            onAddTodo.invoke(newTodoTitle.value)
            newTodoTitle.value = ""
        }) {
            Text("Add Todo")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Button(onClick = onBackup) {
                Text("Backup Todos")
            }

            Button(onClick = onRestore) {
                Text("Restore Todos")
            }
        }

        LazyColumn {
            items(todos) { todo ->
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(todo.title)
                    Button(onClick = { onDeleteTodo.invoke(todo) }) {
                        Text("Delete")
                    }
                }
            }
        }

    }
}
