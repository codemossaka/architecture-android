package ru.godsonpeya.architecture.domain.usecase

import ru.godsonpeya.architecture.domain.model.Todo
import ru.godsonpeya.architecture.domain.repository.TodoRepository

class RestoreTodos(private val repository: TodoRepository) {
    suspend operator fun invoke(): Result<List<Todo>> {
        return repository.restoreTodos()
    }
}
