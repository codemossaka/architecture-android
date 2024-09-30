package ru.godsonpeya.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.godsonpeya.architecture.domain.model.Todo
import ru.godsonpeya.architecture.domain.usecase.*
import javax.inject.Inject

@HiltViewModel
class TodoViewModel  @Inject constructor(
    private val getTodos: GetTodos,
    private val addTodo: AddTodo,
    private val removeTodo: DeleteTodo,
    private val backupTodo: BackupTodos,
    private val restoreTodo: RestoreTodos
) : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    fun loadTodos() {
        viewModelScope.launch {
            _todos.value = getTodos()
        }
    }

    fun addTodo(title: String) {
        viewModelScope.launch {
            addTodo(Todo(title = title))
            loadTodos()
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            removeTodo(todo)
            loadTodos()
        }
    }

    fun backupTodos() {
        viewModelScope.launch {
            backupTodo()
        }
    }

    fun restoreTodos() {
        viewModelScope.launch {
            restoreTodo().onSuccess { todos ->
                _todos.value = todos
            }
        }
    }
}
