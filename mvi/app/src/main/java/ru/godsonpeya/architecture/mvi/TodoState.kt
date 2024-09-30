package ru.godsonpeya.architecture.mvi

import ru.godsonpeya.architecture.model.Todo

data class TodoState(
    val todos: List<Todo> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
