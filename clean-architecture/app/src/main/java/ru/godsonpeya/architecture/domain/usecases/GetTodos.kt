package ru.godsonpeya.architecture.domain.usecase

import ru.godsonpeya.architecture.domain.model.Todo
import ru.godsonpeya.architecture.domain.repository.TodoRepository

class GetTodos(private val repository: TodoRepository) {
    suspend operator fun invoke(): List<Todo> {
        return repository.getAllTodos()
    }
}
