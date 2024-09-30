package ru.godsonpeya.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import ru.godsonpeya.architecture.presentation.TodoViewModel
import ru.godsonpeya.architecture.ui.TodoListScreen
import ru.godsonpeya.architecture.ui.theme.ArchitectureTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArchitectureTheme {
                val todos by todoViewModel.todos.collectAsState()

                TodoListScreen(
                    todos = todos,
                    onAddTodo = { title -> todoViewModel.addTodo(title) },
                    onDeleteTodo = { todo -> todoViewModel.deleteTodo(todo) },
                    onBackup = { todoViewModel.backupTodos() },
                    onRestore = { todoViewModel.restoreTodos() }
                )
            }
        }

        todoViewModel.loadTodos()
    }
}
