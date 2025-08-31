# ⚡ Advanced OOP – Animal Olympics with Threads (Java)

This project is part of the **Advanced Object-Oriented Programming** course.  
It focuses on **Multithreading and Concurrency in Java**, building on top of earlier OOP & GUI exercises.

---

## 📖 Overview
The project simulates an **Animal Olympics Tournament** where multiple animals run in parallel:
- Each animal is managed by its own **thread** (`AnimalThread`).
- Competitions are coordinated by a **Referee** thread.
- Results are collected and displayed dynamically in the GUI.

The core challenge was to combine:
- **Object-Oriented Design** (animals, medals, competitions).
- **Swing GUI** (interactive competition window).
- **Thread Management** (synchronization, `wait/notify`, avoiding race conditions).

---

## ⚙️ Key Classes
- `AnimalThread` – runs each animal independently.  
- `TournamentThread` – manages competition flow.  
- `Referee` – controls start/finish, validates results.  
- `CompetitionFrame` & `ZooPanel` – GUI for managing animals and showing tournaments.  
- `Scores` – tracks medals, results, and rankings.

---

## ▶️ How to Run
1. Open in **IntelliJ IDEA** with **JDK 17+**.
2. Run `CompetitionFrame.main()`.
3. Steps:
   - Add animals (land, water, air) via the GUI.
   - Start tournament → each runs in parallel threads.
   - View referee announcements and results in real time.

---

## ✨ Features
- **Multithreading**: each animal runs concurrently.
- **Synchronization**: `synchronized` methods, `wait()` & `notifyAll()`.
- **Referee & Results**: thread-safe score collection.
- **GUI Integration**: tournament progress shown visually.
- **Scalability**: designed for adding more animal types or competition modes.

---

## 📂 Project Structure
src/
├── animals # Hierarchy of Animal subclasses
├── threads # AnimalThread, TournamentThread, Referee
├── gui # CompetitionFrame, ZooPanel, AddAnimal dialog
├── olympics # Medal, Scores
└── system # Entry point


---

## 📚 What I Learned
- How to manage **threads** safely in Java.  
- Using **wait/notify** for coordination between animals and referee.  
- Integrating **OOP design + GUI + concurrency** into one system.  
- Debugging issues like race conditions and deadlocks.

---

## 👤 Author
**Segev (segevis)**  
[![GitHub](https://img.shields.io/badge/GitHub-segevis-black?logo=github)](https://github.com/segevis)
