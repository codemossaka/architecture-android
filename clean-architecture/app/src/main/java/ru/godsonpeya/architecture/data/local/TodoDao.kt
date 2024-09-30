package ru.godsonpeya.architecture.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    suspend fun getAllTodos(): List<TodoEntity>

    @Insert
    suspend fun insert(vararg todo: TodoEntity)

    @Delete
    suspend fun delete(todo: TodoEntity)
}
