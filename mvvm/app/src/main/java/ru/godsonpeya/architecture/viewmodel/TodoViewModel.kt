package ru.godsonpeya.architecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.godsonpeya.architecture.model.Todo
import ru.godsonpeya.architecture.repository.TodoRepository
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            _todos.value = repository.getAllTodos()
        }
    }

    fun addTodo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = Todo(title = title)
            repository.insertTodo(todo)
            loadTodos()
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todo)
            loadTodos()
        }
    }

    fun backupTodos(onResult: (Result<Unit>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.backupTodos()
            onResult(result)
        }
    }

    fun restoreTodos(onResult: (Result<List<Todo>>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.restoreTodos()
            result.onSuccess { todos ->
                _todos.value = todos
            }
            onResult(result)
        }
    }
}
