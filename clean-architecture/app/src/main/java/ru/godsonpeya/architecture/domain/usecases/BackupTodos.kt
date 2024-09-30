package ru.godsonpeya.architecture.domain.usecase

import ru.godsonpeya.architecture.domain.repository.TodoRepository

class BackupTodos(private val repository: TodoRepository) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.backupTodos()
    }
}
