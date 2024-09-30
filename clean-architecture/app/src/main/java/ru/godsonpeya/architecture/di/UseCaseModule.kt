package ru.godsonpeya.architecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.godsonpeya.architecture.domain.repository.TodoRepository
import ru.godsonpeya.architecture.domain.usecase.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetTodosUseCase(repository: TodoRepository): GetTodos {
        return GetTodos(repository)
    }

    @Singleton
    @Provides
    fun provideAddTodoUseCase(repository: TodoRepository): AddTodo {
        return AddTodo(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteTodoUseCase(repository: TodoRepository): DeleteTodo {
        return DeleteTodo(repository)
    }

    @Singleton
    @Provides
    fun provideBackupTodosUseCase(repository: TodoRepository): BackupTodos {
        return BackupTodos(repository)
    }

    @Singleton
    @Provides
    fun provideRestoreTodosUseCase(repository: TodoRepository): RestoreTodos {
        return RestoreTodos(repository)
    }
}
