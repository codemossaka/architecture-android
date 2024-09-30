package ru.godsonpeya.architecture.mvp

import ru.godsonpeya.architecture.model.Todo

interface TodoContract {
    interface View {
        fun showTodos(todos: List<Todo>)
        fun showError(message: String)
        fun showSuccess(message: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun loadTodos()
        fun addTodo(title: String)
        fun deleteTodo(todo: Todo)
        fun backupTodos()
        fun restoreTodos()
    }
}
