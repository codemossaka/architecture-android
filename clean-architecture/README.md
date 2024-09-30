## **Clean Architecture**

### Clean Architecture

1. **Domain Layer** : Contient la logique métier pure (entités et cas d'utilisation).
2. **Data Layer** : Contient les implémentations concrètes pour accéder aux données (Room, Retrofit).
3. **Presentation Layer** : Contient la logique de présentation (ViewModel ou Presenter) et l'interface utilisateur.
4. **Interface Layer** : Contient les adaptateurs et les interfaces des services externes (API, base de données).

### Nouvelle Structure du Projet :

```
/TodoApp
│
├── /app
│   ├── /src
│   │   ├── /main
│   │   │   ├── /data                        # Data Layer (données locales et réseau)
│   │   │   │   ├── /local                   # Implémentation Room (base de données locale)
│   │   │   │   │   ├── TodoEntity.kt        # Entité Room
│   │   │   │   │   ├── TodoDao.kt           # DAO Room pour les todos
│   │   │   │   │   └── TodoDatabase.kt      # Base de données Room
│   │   │   │   ├── /remote                  # Implémentation Retrofit (API)
│   │   │   │   │   └── TodoApiService.kt    # Interface API Retrofit
│   │   │   │   ├── /repository              # Implémentation des repositories
│   │   │   │   │   └── TodoRepositoryImpl.kt # Implémentation du repository
│   │   │   ├── /domain                      # Domain Layer (logique métier)
│   │   │   │   ├── /model                   # Entités du domaine (Todo)
│   │   │   │   │   └── Todo.kt              # Modèle Todo pour la logique métier
│   │   │   │   ├── /repository              # Interfaces des repositories
│   │   │   │   │   └── TodoRepository.kt    # Interface du repository
│   │   │   │   ├── /usecases                # Cas d'utilisation (logique métier)
│   │   │   │   │   ├── AddTodo.kt           # Cas d'utilisation pour ajouter un todo
│   │   │   │   │   ├── GetTodos.kt          # Cas d'utilisation pour récupérer les todos
│   │   │   │   │   └── DeleteTodo.kt        # Cas d'utilisation pour supprimer un todo
│   │   │   ├── /presentation                # Presentation Layer (Vue et ViewModel)
│   │   │   │   ├── TodoViewModel.kt         # ViewModel pour la logique de présentation
│   │   │   ├── /ui                          # Interface utilisateur (UI)
│   │   │   │   └── TodoListScreen.kt        # Vue Jetpack Compose pour afficher les todos
│   │   │   ├── /di                          # Injection de dépendances (Hilt)
│   │   │   │   ├── DatabaseModule.kt        # Module pour Room
│   │   │   │   └── NetworkModule.kt         # Module pour Retrofit
│   └── build.gradle                         # Configuration des dépendances (Room, Retrofit, Hilt)
```