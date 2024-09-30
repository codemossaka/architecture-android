package ru.godsonpeya.architecture.domain.usecase

import ru.godsonpeya.architecture.domain.model.Todo
import ru.godsonpeya.architecture.domain.repository.TodoRepository

class AddTodo(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) {
        repository.insertTodo(todo)
    }
}
