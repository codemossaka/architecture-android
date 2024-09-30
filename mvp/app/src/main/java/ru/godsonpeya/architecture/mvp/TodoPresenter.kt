package ru.godsonpeya.architecture.mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.godsonpeya.architecture.model.Todo
import ru.godsonpeya.architecture.repository.TodoRepository
import javax.inject.Inject

class TodoPresenter @Inject constructor(
    private val repository: TodoRepository,
    private val view: TodoContract.View
) : TodoContract.Presenter {

    override fun loadTodos() {
        view.showLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val todos = repository.getAllTodos()
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    view.showTodos(todos)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    view.showError("Failed to load todos")
                }
            }
        }
    }

    override fun addTodo(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val todo = Todo(title = title)
                repository.insertTodo(todo)
                loadTodos() // Recharger les todos après l'ajout
                withContext(Dispatchers.Main) {
                    view.showSuccess("Todo added")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showError("Failed to add todo")
                }
            }
        }
    }

    override fun deleteTodo(todo: Todo) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.deleteTodo(todo)
                loadTodos() // Recharger les todos après suppression
                withContext(Dispatchers.Main) {
                    view.showSuccess("Todo deleted")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showError("Failed to delete todo")
                }
            }
        }
    }

    override fun backupTodos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = repository.backupTodos()
                withContext(Dispatchers.Main) {
                    result.onSuccess {
                        view.showSuccess("Backup successful")
                    }.onFailure {
                        view.showError("Backup failed")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showError("Failed to backup todos")
                }
            }
        }
    }

    override fun restoreTodos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = repository.restoreTodos()
                withContext(Dispatchers.Main) {
                    result.onSuccess { todos ->
                        view.showTodos(todos)
                        view.showSuccess("Todos restored")
                    }.onFailure {
                        view.showError("Restore failed")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showError("Failed to restore todos")
                }
            }
        }
    }
}
