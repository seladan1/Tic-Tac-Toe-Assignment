package tdd;

public class Board {
    private int boardSize;
    private char[][] board;

    private Board() {}

    /**
     * Creates a new board with specified size and initializes it
     */
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.setBoard();
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * Initializes the board with empty spaces
     */
    private void setBoard() {
        this.board = new char[this.boardSize][this.boardSize];

        // Fill all positions with empty spaces
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                board[i][j] = ' ';
            }
        }
    }

    public void print() {
        StringBuilder topBottomBoundary = new StringBuilder();

        // Create horizontal border line
        topBottomBoundary.append("+---".repeat(Math.max(0, this.boardSize)));
        topBottomBoundary.append("+");

        // Print each row with vertical separators
        for (char[] row : this.board) {
            System.out.println(topBottomBoundary);

            for (char cell : row) {
                System.out.print("| " + cell + " ");
            }
            System.out.println("|");
        }
        System.out.println(topBottomBoundary);
        System.out.println();
    }

    /**
     * Places a player's marker at the specified position
     * Converts 1-based position to 2D array coordinates
     */
    public void placeTheMove(char checkMark, int movePosition) {
        int i = (movePosition - 1) / this.board.length; // Calculate row
        int j = (movePosition - 1) % this.board.length; // Calculate column
        this.board[i][j] = checkMark;
    }

    /**
     * Checks if the specified player has won the game
     * Tests all rows, columns, and diagonals
     */
    public boolean checkWin(char player) {
        boolean win;

        // Check all rows and columns
        for (int i = 0; i < boardSize; i++) {
            boolean rowWin = true;
            boolean colWin = true;

            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != player) {
                    rowWin = false;
                }
                if (board[j][i] != player) {
                    colWin = false;
                }
            }

            if (rowWin || colWin) {
                return true;
            }
        }

        // Check main diagonal (top-left to bottom-right)
        win = true;
        for (int i = 0; i < boardSize; i++) {
            if (board[i][i] != player) {
                win = false;
                break;
            }
        }
        if (win) {
            return true;
        }

        // Check anti-diagonal (top-right to bottom-left)
        win = true;
        for (int i = 0; i < boardSize; i++) {
            if (board[i][boardSize - 1 - i] != player) {
                win = false;
                break;
            }
        }

        return win;
    }

    /**
     * Validates if a position is legal and available
     * Checks bounds and if the position is empty
     */
    public boolean isValidPosition(String position) {
        try {
            int pos = Integer.parseInt(position);
            int max = boardSize * boardSize;

            // Check if position is within valid range
            if (pos < 1 || pos > max) {
                return false;
            }

            // Convert to array coordinates and check if empty
            int row = (pos - 1) / boardSize;
            int col = (pos - 1) % boardSize;

            return board[row][col] == ' ';
        } catch (NumberFormatException e) {
            return false; // Invalid input format
        }
    }

    public boolean isFull() {
        int count = 0;

        // Count all non-empty positions
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                if (this.board[i][j] != ' ') {
                    count++;
                }
            }
        }
        return count == boardSize * boardSize;
    }
}
