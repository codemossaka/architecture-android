package ru.godsonpeya.architecture.mvi

import ru.godsonpeya.architecture.model.Todo

sealed class TodoIntent {
    data class AddTodoIntent(val title: String) : TodoIntent()
    data class DeleteTodoIntent(val todo: Todo) : TodoIntent()
    object LoadTodosIntent : TodoIntent()
    object BackupTodosIntent : TodoIntent()
    object RestoreTodosIntent : TodoIntent()
}
