## **MVI (Model-View-Intent)**
### Structure du projet

```
/TodoApp
│
├── /app
│   ├── /src
│   │   ├── /main
│   │   │   ├── /java/ru/godsonpeya/architecture
│   │   │   │   ├── /di                               # Dossier pour l'injection de dépendances avec Hilt
│   │   │   │   │   ├── DatabaseModule.kt             # Module Hilt pour configurer Room (base de données locale)
│   │   │   │   │   └── NetworkModule.kt              # Module Hilt pour configurer Retrofit (API réseau)
│   │   │   │   ├── /model                            # Modèles et entités de la base de données
│   │   │   │   │   ├── Todo.kt                       # Entité Todo
│   │   │   │   │   ├── TodoDao.kt                    # Interface DAO pour Room (opérations sur la base de données)
│   │   │   │   │   └── TodoDatabase.kt               # Configuration de la base de données Room
│   │   │   │   ├── /mvi                              # Architecture MVI (Model-View-Intent)
│   │   │   │   │   ├── TodoIntent.kt                 # Définition des intentions (Intentions) pour MVI
│   │   │   │   │   ├── TodoState.kt                  # Définition de l'état (State) de l'UI
│   │   │   │   │   ├── TodoViewModel.kt              # Gestion des intentions et de l'état (ViewModel)
│   │   │   │   ├── /network                          # API pour les appels réseau via Retrofit
│   │   │   │   │   └── TodoApiService.kt             # Service Retrofit pour l'API Todo (sauvegarde et restauration)
│   │   │   │   ├── /repository                       # Gestion des données (accès à Room et Retrofit)
│   │   │   │   │   └── TodoRepository.kt             # Repository pour les opérations sur Todo (DB + API)
│   │   │   │   ├── /view                             # Interface utilisateur avec Jetpack Compose
│   │   │   │   │   └── TodoListScreen.kt             # Écran principal de la liste des todos
│   │   │   │   ├── MainActivity.kt                   # Activité principale de l'application
│   │   │   │   └── TodoApplication.kt                # Classe Application pour Hilt
│   │   ├── /res                                      # Fichiers de ressources (layouts, strings, etc.)
│   ├── /gradle                                       # Fichiers de configuration Gradle
│   └── build.gradle                                  # Fichier de configuration Gradle (dépendances)
```

### Explication détaillée des dossiers

1. **`/di` (Dependency Injection)**
   - **DatabaseModule.kt** : Configure la base de données Room et le DAO pour qu'ils puissent être injectés à l'aide de **Hilt**.
   - **NetworkModule.kt** : Configure Retrofit pour effectuer les appels réseau via l'API de Todo.

2. **`/model`**
   - **Todo.kt** : Définition de l'entité Todo utilisée par Room pour la base de données locale.
   - **TodoDao.kt** : Interface DAO contenant les méthodes pour accéder à la base de données (CRUD sur les Todos).
   - **TodoDatabase.kt** : Classe qui crée et fournit l'instance de la base de données Room.

3. **`/mvi`**
   - **TodoIntent.kt** : Définit les actions (Intentions) que l'utilisateur peut initier, telles que l'ajout ou la suppression de Todos.
   - **TodoState.kt** : Définit l'état actuel de l'UI (par exemple, la liste des todos, si une erreur s'est produite, ou si les données sont en cours de chargement).
   - **TodoViewModel.kt** : Responsable de la gestion des intentions utilisateur, de la mise à jour de l'état de l'UI, et de l'interaction avec le repository.

4. **`/network`**
   - **TodoApiService.kt** : Interface Retrofit définissant les appels réseau pour sauvegarder et restaurer les todos depuis un serveur distant.

5. **`/repository`**
   - **TodoRepository.kt** : Combine les accès à la base de données locale (Room) et les appels réseau (Retrofit). C'est le point central pour accéder aux données et gérer les interactions entre la base de données locale et l'API distante.

6. **`/view`**
   - **TodoListScreen.kt** : Gère l'affichage de l'interface utilisateur avec **Jetpack Compose**. Il observe les changements dans l'état de l'UI et réagit en fonction.

7. **`MainActivity.kt`**
   - L'activité principale qui injecte le ViewModel, initie l'interface utilisateur, et permet à l'utilisateur d'interagir avec la liste des todos.

8. **`TodoApplication.kt`**
   - La classe Application qui est annotée avec `@HiltAndroidApp` pour activer **Hilt** dans toute l'application.