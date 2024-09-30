package ru.godsonpeya.architecture.controller

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.godsonpeya.architecture.model.Todo
import ru.godsonpeya.architecture.repository.TodoRepository
import javax.inject.Inject


class TodoController @Inject constructor(
    private val repository: TodoRepository
) {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos = _todos.asStateFlow()

    init {
        loadTodos()
    }

    private fun loadTodos() {
        CoroutineScope(Dispatchers.IO).launch {
            _todos.value = repository.getAllTodos()
        }
    }

    fun addTodo(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val todo = Todo(title = title)
            repository.insertTodo(todo)
            loadTodos()
        }
    }

    fun deleteTodo(todo: Todo) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteTodo(todo)
            loadTodos()
        }
    }

    // Backup des todos sur le serveur distant
    fun backupTodos(onResult: (Result<Unit>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.backupTodos()
            onResult(result)
        }
    }

    // Restaurer les todos depuis le serveur distant
    fun restoreTodos(onResult: (Result<List<Todo>>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.restoreTodos()
            result.onSuccess { todos ->
                _todos.value = todos
            }
            onResult(result)
        }
    }
}