import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {

    private final char[] board = new char[9]; // '_' empty, 'X' human, 'O' AI
    private final JButton[] buttons = new JButton[9];
    private final char HUMAN = 'X';
    private final char AI = 'O';
    private boolean gameOver = false;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe vs Minimax AI");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        for (int i = 0; i < 9; i++) board[i] = '_';

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton("");
            btn.setFont(new Font("Arial", Font.BOLD, 50));
            final int index = i;
            btn.addActionListener(e -> handleHumanMove(index));
            buttons[i] = btn;
            gridPanel.add(btn);
        }

        add(gridPanel, BorderLayout.CENTER);

        JButton resetBtn = new JButton("Restart Game");
        resetBtn.addActionListener(e -> resetGame());
        add(resetBtn, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleHumanMove(int index) {
        if (gameOver || board[index] != '_') return; // ignore invalid clicks

        board[index] = HUMAN;
        buttons[index].setText(String.valueOf(HUMAN));

        if (checkGameOver(HUMAN)) return;

        // AI's turn
        int aiMove = findBestMove();
        if (aiMove != -1) {
            board[aiMove] = AI;
            buttons[aiMove].setText(String.valueOf(AI));
            checkGameOver(AI);
        }
    }

    private boolean checkGameOver(char player) {
        if (isWinner(board, player)) {
            gameOver = true;
            String msg = (player == HUMAN) ? "You win!" : "AI wins!";
            JOptionPane.showMessageDialog(this, msg);
            return true;
        }
        if (isBoardFull(board)) {
            gameOver = true;
            JOptionPane.showMessageDialog(this, "It's a draw!");
            return true;
        }
        return false;
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            board[i] = '_';
            buttons[i].setText("");
        }
        gameOver = false;
    }

    // ---------- Minimax AI logic ----------

    private int findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (board[i] == '_') {
                board[i] = AI;
                int score = minimax(board, 0, false);
                board[i] = '_'; // undo move
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    // isMaximizing = true means it's AI's turn to pick the best score
    private int minimax(char[] b, int depth, boolean isMaximizing) {
        if (isWinner(b, AI)) return 10 - depth;
        if (isWinner(b, HUMAN)) return depth - 10;
        if (isBoardFull(b)) return 0;

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (b[i] == '_') {
                    b[i] = AI;
                    best = Math.max(best, minimax(b, depth + 1, false));
                    b[i] = '_';
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (b[i] == '_') {
                    b[i] = HUMAN;
                    best = Math.min(best, minimax(b, depth + 1, true));
                    b[i] = '_';
                }
            }
            return best;
        }
    }

    private boolean isWinner(char[] b, char player) {
        int[][] winPatterns = {
            {0,1,2}, {3,4,5}, {6,7,8}, // rows
            {0,3,6}, {1,4,7}, {2,5,8}, // columns
            {0,4,8}, {2,4,6}           // diagonals
        };
        for (int[] pattern : winPatterns) {
            if (b[pattern[0]] == player && b[pattern[1]] == player && b[pattern[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull(char[] b) {
        for (char c : b) {
            if (c == '_') return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
