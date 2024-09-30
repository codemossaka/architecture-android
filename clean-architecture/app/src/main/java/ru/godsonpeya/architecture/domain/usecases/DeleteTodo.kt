package ru.godsonpeya.architecture.domain.usecase

import ru.godsonpeya.architecture.domain.model.Todo
import ru.godsonpeya.architecture.domain.repository.TodoRepository

class DeleteTodo(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) {
        repository.deleteTodo(todo)
    }
}
