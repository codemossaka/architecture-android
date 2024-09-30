package ru.godsonpeya.architecture.repository

import ru.godsonpeya.architecture.model.Todo
import ru.godsonpeya.architecture.model.TodoDao
import javax.inject.Inject


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.godsonpeya.architecture.network.TodoApiService

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao,
    private val todoApiService: TodoApiService
) {

    // Récupérer les todos depuis la base de données locale
    suspend fun getAllTodos(): List<Todo> {
        return todoDao.getAllTodosList()
    }

    // Sauvegarder un todo dans la base de données locale
    suspend fun insertTodo(todo: Todo) {
        todoDao.insert(todo)
    }

    // Supprimer un todo dans la base de données locale
    suspend fun deleteTodo(todo: Todo) {
        todoDao.delete(todo)
    }

    // Sauvegarder les todos sur le serveur distant (Backup)
    suspend fun backupTodos(): Result<Unit> {
        val todos = todoDao.getAllTodosList()
        return withContext(Dispatchers.IO) {
            val response = todoApiService.backupTodos(todos)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to backup todos"))
            }
        }
    }

    // Récupérer les todos du serveur distant
    suspend fun restoreTodos(): Result<List<Todo>> {
        return withContext(Dispatchers.IO) {
            val response = todoApiService.getTodos()
            if (response.isSuccessful) {
                response.body()?.let { todos ->
                    todoDao.insert(*todos.toTypedArray()) // Insérer les todos restaurés dans la base de données locale
                    Result.success(todos)
                } ?: Result.failure(Exception("No data"))
            } else {
                Result.failure(Exception("Failed to fetch todos"))
            }
        }
    }
}
