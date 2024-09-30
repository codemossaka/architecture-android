package ru.godsonpeya.architecture.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.godsonpeya.architecture.model.Todo

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): Response<List<Todo>>

    @POST("todos/backup")
    suspend fun backupTodos(@Body todos: List<Todo>): Response<Void>
}
