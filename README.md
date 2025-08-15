# Trendora
**Learning Android Architecture with MVVM & Jetpack Compose**

A modern e-commerce Android application built to demonstrate **MVVM Architecture**, **Jetpack Compose**, **Dependency Injection**, and **Testing** best practices.

## 📱 Screenshots
*Screenshots will be added as we progress through sessions*

---

## 🎯 Project Goals
This project is designed as a hands-on learning experience across 4 sessions:

### Session 1: Architecture Foundation ✅
- MVVM pattern implementation
- StateFlow for state management
- Jetpack Compose UI
- Mock data repository

### Session 2: Patterns & Dependency Injection 🔄
- Real API integration with Retrofit
- Hilt for dependency injection
- Repository pattern
- Observer, Strategy, and Command patterns

### Session 3: Testing Implementation 📋
- Unit testing with JUnit & Mockito
- ViewModel testing
- Repository testing
- Coroutine testing

### Session 4: Build Configuration & Advanced Features 🚀
- Gradle build variants
- Product flavors (Free/Pro)
- Shopping cart functionality
- Final deployment setup

---

## 🏗️ Architecture Overview

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   UI Layer      │    │  Domain Layer   │    │   Data Layer    │
│                 │    │                 │    │                 │
│  • Composables  │◄──►│  • ViewModels   │◄──►│  • Repository   │
│  • Navigation   │    │  • Use Cases    │    │  • Data Sources │
│  • Themes       │    │  • Models       │    │  • API/Database │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### 🛠️ Tech Stack

| Layer | Technologies |
|-------|-------------|
| **UI** | Jetpack Compose, Navigation Component, Material3 |
| **Architecture** | MVVM, StateFlow, ViewModel |
| **Dependency Injection** | Hilt (koin) |
| **Network** | Retrofit |
| **Local Storage** | Room Database, DataStore |
| **Testing** | JUnit, Mockito, Truth, Coroutine Test |
| **Build** | Gradle KTS, Version Catalogs |

---

## 📦 Project Structure

```
app/src/main/java/com/trendora/
├── 📁 data/                    # Data Layer
│   ├── 📁 local/              # Local data sources
│   │   ├── 📁 database/       # Room database
│   │   └── 📁 preferences/    # DataStore preferences
│   ├── 📁 remote/             # Remote data sources  
│   │   ├── 📁 api/            # API interfaces
│   │   └── 📁 dto/            # Data transfer objects
│   └── 📁 repository/         # Repository implementations
├── 📁 domain/                  # Domain Layer (Session 2+)
│   ├── 📁 model/              # Domain models
│   ├── 📁 repository/         # Repository interfaces
│   └── 📁 usecase/            # Use cases
├── 📁 ui/                      # UI Layer
│   ├── 📁 components/         # Reusable composables
│   ├── 📁 screens/            # Screen composables
│   │   ├── 📁 home/
│   │   ├── 📁 product/
│   │   ├── 📁 cart/
│   │   └── 📁 profile/
│   ├── 📁 navigation/         # Navigation setup
│   └── 📁 theme/             # UI theming
├── 📁 viewmodel/              # ViewModels
├── 📁 di/                     # Dependency injection modules
└── 📁 utils/                  # Utility classes
```

---

## 🎯 Features by Session

### ✅ Session 1 Features
- [x] Product listing with grid/list view
- [x] Basic MVVM architecture
- [x] StateFlow state management
- [x] Mock data repository
- [x] Material3 UI components
- [x] Loading states

### 🔄 Session 2 Features (In Progress)
- [ ] Real API integration (FakeStore API)
- [ ] Hilt dependency injection
- [ ] Repository pattern
- [ ] Product details screen
- [ ] Error handling
- [ ] Network status handling

### 📋 Session 3 Features (Planned)
- [ ] Unit tests for ViewModels
- [ ] Repository testing
- [ ] UI testing basics
- [ ] Test coverage reporting
- [ ] Continuous integration setup

### 🚀 Session 4 Features (Planned)  
- [ ] Shopping cart functionality
- [ ] User authentication
- [ ] Build variants (Debug/Release)
- [ ] Product flavors (Free/Pro)
- [ ] App signing configuration

---

**Happy Coding! 🚀**

*"The best way to learn architecture is to build something real with it."*
