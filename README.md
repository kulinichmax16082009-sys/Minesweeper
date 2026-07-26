# Minesweeper Remake

This is a 2D remake of the popular game **Minesweeper**. The game was developed in Java using the IntelliJ IDEA IDE. It is built with Java Swing, which provides a smooth desktop gaming experience.

## Features

- All game statistics can be saved and loaded.
- All game settings are automatically saved.
- Three difficulty levels: Easy, Medium, and Hard.
- Use the mouse to reveal or flag cells.
- Sound effects and background music in the main menu.
- The player wins by opening all the cells without mines. The player looses by opening at least one mine.
- The player's score and time are updated after each revealed cell.
- The first revealed cell is always safe.

## Controls

- **Left-click on the board:** Reveal a cell.
- **Right-click on the board:** Flag or unflag a cell.
- **Double left-click on a statistics row:** Delete that game record.
- **Right-click on a statistics row:** Open the saved game board.

## How to Run

### Running the `.jar` file

1. Create a folder (for example, `game`).
2. Place the `.jar` file and the `resources` folder inside it.
3. Open Terminal and navigate to the folder:

   ```bash
   cd <folder-path>
   ```

4. Run the application:

   ```bash
   java -jar <file-name>.jar
   ```

### Alternative way

1. Repeat steps 1 and 2 above.
2. Double-click the `.jar` file.

### Running the `.exe` file

1. Create a folder (for example, `game`).
2. Place the `.exe` file, the `resources` folder, and the JDK 17 folder named `jre` into it.
3. Double-click the `.exe` file.

### Easiest way

1. Download the latest release from the **Releases** section.
2. Double-click the `.exe` file.

## Requirements

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or later.
- [Windows Terminal](https://apps.microsoft.com/detail/9n0dx20hk701?hl=ru-RU&gl=CZ) (optional if you want to run the `.jar` file from the command line).
- Basic knowledge of the original Minesweeper rules: https://en.wikipedia.org/wiki/Minesweeper_(video_game)
