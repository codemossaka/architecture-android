package ru.godsonpeya.architecture.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAllTodosList(): List<Todo>

    @Insert
    suspend fun insert(vararg todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}
