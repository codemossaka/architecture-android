package ru.godsonpeya.architecture.domain.repository

import ru.godsonpeya.architecture.domain.model.Todo

interface TodoRepository {
    suspend fun getAllTodos(): List<Todo>
    suspend fun insertTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
    suspend fun backupTodos(): Result<Unit>
    suspend fun restoreTodos(): Result<List<Todo>>
}
