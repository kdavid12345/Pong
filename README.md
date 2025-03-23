# Pong Game

## Overview
This project is a **Java-based Pong game** implemented using **Swing** for the graphical user interface. The game features single-player and multiplayer modes, a scoring system, and a high-score leaderboard. It includes sound effects and background music, as well as customizable settings for volume control. The game is designed to be simple yet engaging, providing a classic Pong experience with modern enhancements.

---

## Features
- **Game Modes**:
  - **Single-Player**: Play against an AI-controlled paddle.
  - **Multiplayer**: Play against a friend with separate controls for each player.
- **Scoring System**:
  - Track scores for both players in multiplayer mode.
  - Save and display high scores in a leaderboard.
- **Sound Effects**:
  - Background music and sound effects for ball collisions.
- **Customizable Settings**:
  - Adjust the volume of background music.
- **User-Friendly Interface**:
  - Simple menu navigation with buttons for starting the game, viewing high scores, and adjusting settings.

---

## Code Structure
- **GameFrame.java**: The main frame of the application, managing the card layout for different screens (main menu, game panel, settings, high scores).
- **MainMenu.java**: The main menu panel where users can start the game, view high scores, or adjust settings.
- **GamePanel.java**: The game panel where the Pong game is played. Handles game logic, rendering, and user input.
- **HighScores.java**: Manages the high-score leaderboard, including loading and saving scores to a file.
- **Settings.java**: Allows users to adjust the volume of background music.
- **AudioPlayer.java**: Handles playback of background music and sound effects.
- **Ball.java**: Represents the ball in the game, handling movement and collision detection.
- **Paddle.java**: Represents the paddles, handling movement and collision detection.
- **Score.java**: Represents a player's score, used in the high-score system.

---

## Technical Details
- **Graphics**: The game uses **Java Swing** for rendering the game elements (ball, paddles, scores, etc.).
- **Collision Detection**: Simple rectangle-based collision detection is used for ball-paddle and ball-wall interactions.
- **AI Paddle**: In single-player mode, the AI-controlled paddle follows the ball's vertical position.
- **High-Score System**: High scores are stored in a text file (`data/highscores.txt`) and loaded/saved using file I/O operations.
- **Sound**: Background music and sound effects are played using the `javax.sound.sampled` library.

---

## How to Play
- **Single-Player**:
  - Use the `W` and `S` keys to control the left paddle.
  - The right paddle is controlled by the AI.
- **Multiplayer**:
  - Player 1 uses the `W` and `S` keys to control the left paddle.
  - Player 2 uses the `UP` and `DOWN` arrow keys to control the right paddle.
- **Game Over**:
  - In single-player mode, the game ends when the ball passes the left paddle.
  - In multiplayer mode, the game ends when one player reaches 5 points.

---

## Credits
- Developed as a learning project to practice Java and Swing.
