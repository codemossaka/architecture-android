package ru.godsonpeya.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.godsonpeya.architecture.ui.theme.ArchitectureTheme
import ru.godsonpeya.architecture.viewmodel.TodoViewModel
import ru.godsonpeya.architecture.view.TodoListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArchitectureTheme {
                TodoListScreen(viewModel = todoViewModel)
            }
        }
    }
}
