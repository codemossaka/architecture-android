package ru.godsonpeya.architecture.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _state = MutableStateFlow(TodoState())
    val state: StateFlow<TodoState> = _state

    fun handleIntent(intent: TodoIntent) {
        when (intent) {
            is TodoIntent.AddTodoIntent -> addTodo(intent.title)
            is TodoIntent.DeleteTodoIntent -> deleteTodo(intent.todo)
            is TodoIntent.LoadTodosIntent -> loadTodos()
            is TodoIntent.BackupTodosIntent -> backupTodos()
            is TodoIntent.RestoreTodosIntent -> restoreTodos()
        }
    }

    private fun loadTodos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val todos = repository.getAllTodos()
            _state.value = _state.value.copy(todos = todos, isLoading = false)
        }
    }

    private fun addTodo(title: String) {
        viewModelScope.launch {
            repository.insertTodo(Todo(title = title))
            loadTodos() // Reload todos after adding
        }
    }

    private fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
            loadTodos() // Reload todos after deletion
        }
    }

    private fun backupTodos() {
        viewModelScope.launch {
            repository.backupTodos().onSuccess {
                _state.value = _state.value.copy(isLoading = false)
            }.onFailure {
                _state.value = _state.value.copy(errorMessage = "Failed to backup todos")
            }
        }
    }

    private fun restoreTodos() {
        viewModelScope.launch {
            repository.restoreTodos().onSuccess { todos ->
                _state.value = _state.value.copy(todos = todos, isLoading = false)
            }.onFailure {
                _state.value = _state.value.copy(errorMessage = "Failed to restore todos")
            }
        }
    }
}
