# Nebula-Breaker

Nebula-Breaker is a classic arcade-style space shooter game built in Java with a modern UI. Battle waves of enemy spaceships, dodge bullets, and aim for the highest score!

---

## Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
- [How to Play](#how-to-play)
- [Installation & Usage](#installation--usage)
- [Running on Windows, Linux, and macOS](#running-on-windows-linux-and-macos)
- [Game Controls](#game-controls)
- [Settings & Leaderboard](#settings--leaderboard)
- [Troubleshooting](#troubleshooting)
- [Credits](#credits)

---

## Features

- Smooth 2D space shooter gameplay
- Multiple difficulty levels (Easy, Medium, Hard)
- Sound and music controls
- Leaderboard to track high scores
- Modern, visually appealing UI

---

## Screenshots

*Home Screen*
<br/>
<img width="349" height="598" alt="image" src="https://github.com/user-attachments/assets/3d27d1a7-7fca-4205-a6d3-bd91979d0bb5" />

*Gameplay*
<br/>
<img width="346" height="587" alt="image" src="https://github.com/user-attachments/assets/c1d408e9-8c83-4341-b46a-a39c8c5ab49e" />

*Setting*
<br/>
<img width="346" height="601" alt="image" src="https://github.com/user-attachments/assets/9a23d917-2090-4e43-bd75-9c1f0d056615" />

*Leader Board*
<br/>
<img width="346" height="600" alt="image" src="https://github.com/user-attachments/assets/75129acc-ca64-47a3-ab76-645017b66064" />

---

## How to Play

- Use **Arrow keys** or **WASD** to move your spaceship.
- Press **Spacebar** to shoot.
- Avoid enemy bullets and destroy enemy ships to score points.
- The game ends when you lose all your lives.

---

## Installation & Usage

### Prerequisites

- Java JDK 8 or higher installed on your system.
- (Optional) Bash shell for running the build script on Linux/macOS.

### Building and Running (Linux/macOS)

1. **Clone the repository:**
   ```bash
   git clone https://github.com/BiTech5/Nebula-Breaker
   cd Nebula-Breaker
   ```

2. **Build and run using the provided Bash script:**
   ```bash
   ./build.sh
   ```
   This script will:
   - Create a `bin` directory if it doesn't exist.
   - Compile all Java source files from `src/` into `bin/`.
   - Run the game.

### Building and Running (Windows)

1. **Open Command Prompt or PowerShell.**
2. **Navigate to the project directory:**
   ```cmd
   cd path\to\Nebula-Breaker
   ```
3. **Compile the Java files:**
   ```cmd
   mkdir bin
   javac -d bin src\main\Main.java src\core\*.java src\util\*.java src\view\*.java
   ```
   Or, to compile all Java files recursively:
   ```cmd
   javac -d bin $(dir /s /b src\*.java)
   ```
   *(If using Git Bash or WSL, you can use the provided `build.sh` script.)*

4. **Run the game:**
   ```cmd
   cd bin
   java src.main.Main
   ```

### Running on macOS

- The process is the same as Linux. Make sure you have Java installed and use the `build.sh` script.

---

## Game Controls

| Action         | Key(s)         |
|----------------|----------------|
| Move Left      | Left Arrow / A |
| Move Right     | Right Arrow / D|
| Move Up        | Up Arrow / W   |
| Move Down      | Down Arrow / S |
| Shoot          | Spacebar       |
| Pause/Exit     | ESC (via UI)   |

---

## Settings & Leaderboard

- **Settings:** Adjust difficulty and toggle music from the Settings page.
- **Leaderboard:** View high scores for each difficulty level.

---

## Troubleshooting

- **No sound/music?**  
  Ensure your system supports Java sound and the `assets/sounds/` files exist.
- **Game won’t start?**  
  Make sure all dependencies are present and Java is installed correctly.
- **Images not loading?**  
  Check that the `assets/images/` directory exists and contains the required images.

---

## Credits

- Sound and image assets from sora
- Inspired by classic arcade shooters

---

## License

This project is licensed under the MIT License.
