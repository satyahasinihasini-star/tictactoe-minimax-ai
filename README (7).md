# Tic-Tac-Toe with Minimax AI

A Java Swing implementation of Tic-Tac-Toe where the computer opponent uses the **Minimax algorithm** to play optimally. The AI never loses — at best, you can force a draw.

## Features

- Simple GUI built with Java Swing (3x3 clickable grid)
- Unbeatable AI opponent powered by Minimax
- Win, loss, and draw detection
- Restart button to play again without relaunching

## How It Works

- The board is represented as a 9-element `char` array (`X` = human, `O` = AI, `_` = empty).
- After every human move, the AI calls `findBestMove()`.
- `findBestMove()` tries each empty cell and scores it using `minimax()`, which recursively simulates all possible future moves assuming both players play optimally.
- Winning faster is scored higher (`10 - depth`), and losing slower is scored less negative (`depth - 10`), so the AI also prefers quicker wins and slower losses.

## Requirements

- Java JDK 8 or above (tested on OpenJDK 21)

## How to Run

```bash
javac TicTacToe.java
java TicTacToe
```

A window will open with a 3x3 grid. Click any cell to play as `X`; the AI automatically responds as `O`.

## Possible Improvements

- Add **Alpha-Beta pruning** to reduce the number of states evaluated
- Add difficulty levels (e.g., random AI vs Minimax AI)
- Extend to a larger board size (e.g., 4x4 or 5x5 with a win-length rule)

## Author

Pravallika Satya Hasini
