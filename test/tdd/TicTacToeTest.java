package tdd;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the TicTacToe game logic and validation methods.
 */
public class TicTacToeTest {

    /**
     * Tests that checkWin correctly identifies a winning row, column, main diagonal,
     * and anti-diagonal for board sizes from 3 to 10.
     */
    @Test
    public void checkWinnerTest() {
        // Test all possible board sizes.
        for (int size = 3; size <= 10; size++) {
            Board board = new Board(size);

            // Test horizontal win for 'X'
            for (int col = 1; col <= size; col++) {
                board.placeTheMove('X', col);
            }
            assertTrue("Player X should win with full top row on size " + size,
                    board.checkWin('X'));

            // Test horizontal win for 'X'
            Board board2 = new Board(size);
            for (int row = 0; row < size; row++) {
                board2.placeTheMove('O', 1 + row * size);
            }
            assertTrue("Player O should win with full first column on size " + size,
                    board2.checkWin('O'));

            // Test main diagonal win for 'X'
            Board board3 = new Board(size);
            for (int diagonal = 0; diagonal < size; diagonal++) {
                board3.placeTheMove('X', 1 + diagonal * (size + 1));
            }
            assertTrue("Player X should win with main diagonal on size " + size,
                    board3.checkWin('X'));

            // Test anti-diagonal win for 'O'
            Board board4 = new Board(size);
            for (int i = 0; i < size; i++) {
                int row = i;
                int col = size - 1 - i;
                int pos = row * size + col + 1;
                board4.placeTheMove('O', pos);
            }
            assertTrue("Player O should win with anti-diagonal on size " + size,
                    board4.checkWin('O'));
        }
    }

    /**
     * Tests that handleWinner properly increments the win count for the specified player.
     */
    @Test
    public void handleWinnerTest() {
        // Test that handleWinner increases the win count of the winning player
        Player player1 = new Player("Test Player1", 1, 'X');
        Player player2 = new Player("Test Player2", 2, 'O');
        TicTacToe game = new TicTacToe(player1, player2);
        int before = player1.getNumberOfWins();

        game.handleWinner(player1);
        assertTrue("Player's win count should have increased by 1",
                before + 1 == player1.getNumberOfWins());

    }

    /**
     * Tests that isValidPosition returns correct results for:
     * - valid positions
     * - invalid strings and characters
     * - out-of-bounds positions
     * - taken spots
     */
    @Test
    public void isValidPositionTest() {
        Board board = new Board(3);

        // Test valid input
        assertTrue("Position 1 should be valid", board.isValidPosition("1"));

        // Test invalid inputs
        assertFalse("Negative number should be invalid", board.isValidPosition("-1"));
        assertFalse("Zero should be invalid", board.isValidPosition("0"));
        assertFalse("Non-numeric input should be invalid", board.isValidPosition("A"));
        assertFalse("Out of bounds input should be invalid", board.isValidPosition("10"));
        assertFalse("Input with whitespace should be invalid", board.isValidPosition(" 1 "));
        assertFalse("Alphanumeric input should be invalid", board.isValidPosition("1a"));

        // Test already taken spot
        board.placeTheMove('X', 1);
        assertFalse("Taken spot should be invalid", board.isValidPosition("1"));
    }

    /**
     * Tests isFull by checking both an empty and a full board state.
     */
    @Test
    public void isFullTest() {
        Board board = new Board(3);

        // Test that an empty board is not full
        assertFalse("Board is empty, should not be full", board.isFull());

        // Fill the board completely
        for (int i = 1; i <= 9; i++) {
            board.placeTheMove((i % 2 == 0) ? 'X' : 'O', i);
        }

        // Now the board should be full
        assertTrue("Board should be full", board.isFull());
    }

    /**
     * Tests that verifyBoardSize accepts only valid board sizes (3-10)
     * and rejects all other values including strings and invalid numbers.
     */
    @Test
    public void verifyBoardSizeTest() {
        TicTacToe game = new TicTacToe();

        // Valid board sizes (3 to 10)
        for (int size = 3; size <= 10; size++) {
            assertTrue("Size" + size + "should be valid", game.verifyBoardSize(Integer.toString(size)));
        }

        // Invalid board sizes and formats
        assertFalse("Size 2 should not be valid", game.verifyBoardSize("2"));
        assertFalse("Size 11 should not be valid", game.verifyBoardSize("11"));
        assertFalse("Non-integer input should not be valid", game.verifyBoardSize("abc"));
        assertFalse("Input with leading/trailing whitespace should be invalid", game.verifyBoardSize(" 5 "));
        assertFalse("Empty string should be invalid", game.verifyBoardSize(""));
    }
}