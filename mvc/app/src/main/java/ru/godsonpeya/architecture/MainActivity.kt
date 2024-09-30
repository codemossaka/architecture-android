package ru.godsonpeya.architecture

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import ru.godsonpeya.architecture.controller.TodoController
import ru.godsonpeya.architecture.ui.theme.ArchitectureTheme
import ru.godsonpeya.architecture.view.TodoListScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var todoController: TodoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchitectureTheme {
                val todos by todoController.todos.collectAsState()

                TodoListScreen(
                    todos = todos,
                    onAddTodo = { title -> todoController.addTodo(title) },
                    onDeleteTodo = { todo -> todoController.deleteTodo(todo) },
                    onBackup = {
                        todoController.backupTodos { result ->
                            result.onSuccess {
                                Toast.makeText(this, "Backup successful", Toast.LENGTH_SHORT).show()
                            }.onFailure {
                                Toast.makeText(this, "Backup failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    onRestore = {
                        todoController.restoreTodos { result ->
                            result.onSuccess {
                                Toast.makeText(this, "Todos restored", Toast.LENGTH_SHORT).show()
                            }.onFailure {
                                Toast.makeText(this, "Restore failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                )
            }
        }
    }
}
