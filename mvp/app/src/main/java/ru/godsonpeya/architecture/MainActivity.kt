package ru.godsonpeya.architecture

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.godsonpeya.architecture.model.Todo
import ru.godsonpeya.architecture.mvp.TodoContract
import ru.godsonpeya.architecture.mvp.TodoPresenter
import ru.godsonpeya.architecture.ui.theme.ArchitectureTheme
import ru.godsonpeya.architecture.view.TodoListScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), TodoContract.View {

    @Inject
    lateinit var presenter: TodoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.loadTodos()

        setContent {
            ArchitectureTheme {
                TodoListScreen(
                    todos = emptyList(),  // Initialement vide, les todos seront chargés par le Presenter
                    onAddTodo = { title -> presenter.addTodo(title) },
                    onDeleteTodo = { todo -> presenter.deleteTodo(todo) },
                    onBackup = { presenter.backupTodos() },
                    onRestore = { presenter.restoreTodos() }
                )
            }
        }
    }

    override fun showTodos(todos: List<Todo>) {
        setContent {
            ArchitectureTheme {
                TodoListScreen(
                    todos = todos,
                    onAddTodo = { title -> presenter.addTodo(title) },
                    onDeleteTodo = { todo -> presenter.deleteTodo(todo) },
                    onBackup = { presenter.backupTodos() },
                    onRestore = { presenter.restoreTodos() }
                )
            }
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        // Optionnel : Implémenter un indicateur de chargement
    }

    override fun hideLoading() {
        // Optionnel : Masquer l'indicateur de chargement
    }
}
