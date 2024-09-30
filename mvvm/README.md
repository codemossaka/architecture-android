## MVVM (Model-View-ViewModel)

```
/TodoApp
│
├── /app
│   ├── /src
│   │   ├── /main
│   │   │   ├── /java/ru/godsonpeya/architecture
│   │   │   │   ├── /di                           # Modules Hilt pour DI
│   │   │   │   │   ├── DatabaseModule.kt         # Module Hilt pour Room (base de données locale)
│   │   │   │   │   └── NetworkModule.kt          # Module Hilt pour Retrofit (API réseau)
│   │   │   │   ├── /model                        # Modèles et entités
│   │   │   │   │   ├── Todo.kt                   # Entité Todo
│   │   │   │   │   ├── TodoDao.kt                # Interface DAO pour Room (opérations CRUD sur les Todos)
│   │   │   │   │   └── TodoDatabase.kt           # Configuration de la base de données Room
│   │   │   │   ├── /network                      # API pour les appels réseau via Retrofit
│   │   │   │   │   └── TodoApiService.kt         # Service API pour effectuer les appels réseau
│   │   │   │   ├── /repository                   # Gestion des données (accès à Room et Retrofit)
│   │   │   │   │   └── TodoRepository.kt         # Repository pour centraliser la gestion des données Todo
│   │   │   │   ├── /view                         # Interface utilisateur avec Jetpack Compose
│   │   │   │   │   └── TodoListScreen.kt         # Composant UI principal (écran des Todos)
│   │   │   │   ├── /viewmodel                    # Logique de gestion d'état et des actions utilisateur (MVVM)
│   │   │   │   │   └── TodoViewModel.kt          # ViewModel pour gérer les actions et l'état des Todos
│   │   │   │   ├── MainActivity.kt               # Activité principale qui injecte le ViewModel
│   │   │   │   └── TodoApplication.kt            # Classe Application configurée avec Hilt
│   ├── /res                                      # Fichiers de ressources (layouts, strings, etc.)
│   ├── /gradle                                   # Configuration Gradle
│   └── build.gradle                              # Fichier de configuration Gradle pour les dépendances
```

### Détail des dossiers :

1. **`/di`** :
    - **`DatabaseModule.kt`** : Configure l'injection de dépendances pour Room (la base de données locale).
    - **`NetworkModule.kt`** : Configure l'injection de Retrofit pour les appels réseau.

2. **`/model`** :
    - **`Todo.kt`** : L'entité Todo représente une tâche à faire dans la base de données.
    - **`TodoDao.kt`** : Interface DAO définissant les opérations CRUD pour la gestion des Todos.
    - **`TodoDatabase.kt`** : Configuration de la base de données Room avec les entités et les DAO.

3. **`/network`** :
    - **`TodoApiService.kt`** : Interface Retrofit définissant les appels réseau pour sauvegarder et restaurer les todos.

4. **`/repository`** :
    - **`TodoRepository.kt`** : Le repository qui centralise l'accès aux données locales (Room) et distantes (API avec Retrofit).

5. **`/view`** :
    - **`TodoListScreen.kt`** : Interface utilisateur avec Jetpack Compose, affichant la liste des todos et gérant les interactions utilisateur (ajout, suppression, backup, etc.).

6. **`/viewmodel`** :
    - **`TodoViewModel.kt`** : Le ViewModel qui gère l'état des todos et les actions comme l'ajout, la suppression, et la synchronisation avec le serveur.

7. **`MainActivity.kt`** :
    - L'activité principale où le ViewModel est injecté via Hilt et où l'interface utilisateur est définie avec Jetpack Compose.

8. **`TodoApplication.kt`** :[README.md](..%2F..%2Farchitecture-android%2FREADME.md)
    - Classe `Application` annotée avec `@HiltAndroidApp` pour configurer l'injection de dépendances dans toute l'application.

---

Cette structure sépare bien les responsabilités, avec une gestion claire de l'état dans le **ViewModel**, des opérations CRUD dans le **Repository**, et une interface réactive gérée avec **Jetpack Compose**.