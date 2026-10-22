# Tic-Tac-Toe: TDD & OOP Project 🎮

A customizable, console-based Tic-Tac-Toe game implemented in Java. 
This project was developed as part of an Object-Oriented Programming (OOP) course during my first year of studies. The primary focus of the assignment was implementing features using **Test-Driven Development (TDD)** and designing the system architecture with **UML diagrams**.

## 🚀 Features

* **Dynamic Board Size:** Play on any grid size ranging from standard `3x3` up to `10x10`.
* **Multiple Game Modes:** Before starting, choose the number of human players (0-2):
  * `0`: Simulation mode (AutoPlayer vs. AutoPlayer).
  * `1`: Single-player mode (Human vs. AutoPlayer).
  * `2`: Local multiplayer (Human vs. Human).
* **Score Tracking:** The game tracks wins for each player across multiple rounds and announces the overall winner when the players decide to quit.
* **AutoPlayer AI:** A randomized automated player designed to make quick, valid moves without delay, especially useful for larger boards.
* **Robust Validation:** Comprehensive input handling to prevent overriding existing marks or choosing out-of-bound cells.

## 🛠️ Development Approach

* **Test-Driven Development (TDD):** Core game logic (win validation, board boundaries, score handling) was developed strictly after writing the respective **JUnit** tests. 
* **UML Design:** The repository includes Class and Sequence diagrams detailing the relationships between the `TicTacToe`, `Board`, `Player`, and `AutoPlayer` classes, as well as the game flow.

## ⚙️ How to Run & Test

The project uses **Apache Ant** for building and testing. 

To compile the code, run:
```bash
ant compile
```

To execute the JUnit tests and verify the TDD implementations, run:
```bash
ant test
```

## 📁 Repository Structure

* `src/tdd/` - Contains the Java source files (`Main`, `TicTacToe`, `Board`, `Player`, `AutoPlayer`).
* `test/tdd/` - Contains the JUnit test files (`TicTacToeTest`, `AutoPlayerTest`).
* `build.xml` - Configuration file for Apache Ant.
* `UML/` - Contains the Class and Sequence diagrams.
