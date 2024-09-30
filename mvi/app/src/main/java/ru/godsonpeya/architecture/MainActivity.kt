package ru.godsonpeya.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.godsonpeya.architecture.view.TodoListScreen
import ru.godsonpeya.architecture.mvi.TodoViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListScreen(viewModel = viewModel)
        }
    }
}
