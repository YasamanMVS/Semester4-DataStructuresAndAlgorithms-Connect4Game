# Connect 4 Game

## Project Overview:
This project implements the classic game Connect 4 in Java. The game can be played in a two-player mode or against an AI. It utilizes a custom `Board` class to manage the game state and an `AIPlayer` class for the artificial intelligence opponent.

## Features:
- **Two Game Modes**: Play against another person or against a computer AI.
- **Custom Game Board**: The game logic handles board state management, ensuring moves are valid and checking for a win condition.
- **AI Opponent**: The AI uses a minimax algorithm with alpha-beta pruning to determine its moves, making it a challenging opponent.

## Installation:
Ensure you have Java installed (Java 8 or newer recommended).To run this project, clone the repo and compile the Java files:
```bash
git clone https://github.com/YasamanMVS/Semester4-DataStructuresAndAlgorithms-Connect4Game.git
cd Connect4Game
javac src/*.java
```
## Usage:
To start the game, run the `Main` class:
```bash
java -cp src Main
```
Follow the on-screen prompts to choose between two-player and AI modes, input player names, and make your moves by selecting columns on the board.

## Project Structure:
- **src/Main.java**: Contains the main method and handles user interactions.
- **src/game/Board.java**: Manages the game board and checks for win conditions.
- **src/ai/AIPlayer.java**: Implements AI logic using the minimax algorithm.
