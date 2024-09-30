package ru.godsonpeya.architecture.data.repository

import ru.godsonpeya.architecture.data.local.TodoDao
import ru.godsonpeya.architecture.data.remote.TodoApiService
import ru.godsonpeya.architecture.domain.model.Todo
import ru.godsonpeya.architecture.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.godsonpeya.architecture.data.local.TodoEntity
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val todoApiService: TodoApiService
) : TodoRepository {

    override suspend fun getAllTodos(): List<Todo> {
        return todoDao.getAllTodos().map { it.toDomain() }
    }

    override suspend fun insertTodo(todo: Todo) {
        todoDao.insert(todo.toEntity())
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.delete(todo.toEntity())
    }

    override suspend fun backupTodos(): Result<Unit> {
        val todos = todoDao.getAllTodos().map { it.toDomain() }
        return withContext(Dispatchers.IO) {
            val response = todoApiService.backupTodos(todos)
            if (response.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("Backup failed"))
        }
    }

    override suspend fun restoreTodos(): Result<List<Todo>> {
        return withContext(Dispatchers.IO) {
            val response = todoApiService.getTodos()
            if (response.isSuccessful) {
                response.body()?.let { todos ->
                    todoDao.insert(*todos.map { it.toEntity() }.toTypedArray())
                    Result.success(todos)
                } ?: Result.failure(Exception("No data"))
            } else {
                Result.failure(Exception("Restore failed"))
            }
        }
    }

    private fun Todo.toEntity(): TodoEntity = TodoEntity(id, title, isDone)
    private fun TodoEntity.toDomain(): Todo = Todo(id, title, isDone)
}
