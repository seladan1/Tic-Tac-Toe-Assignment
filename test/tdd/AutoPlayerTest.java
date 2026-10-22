package tdd;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for AutoPlayer behavior.
 */
public class AutoPlayerTest {

    /**
     * Tests that the AutoPlayer places exactly one move on the board when move() is called.
     * This is done by comparing the number of free cells before and after the move.
     */
    @Test
    public void autoPlayerMoveTest() {
        // Create a 3x3 board and an AutoPlayer
        Board board = new Board(3);
        AutoPlayer auto = new AutoPlayer("Auto Test", 1, 'X');

        // Count number of available (free) cells before and after the move
        int before = countFreeCells(board);
        auto.move(board);
        int after = countFreeCells(board);

        // Verify that exactly one cell has been filled
        assertTrue("AutoPlayer should have placed one move on the board", after == before - 1);
    }

    /**
     * Counts the number of empty cells on the board using isValidPosition.
     * This ensures that we're checking both bounds and whether the cell is already taken.
     */
    private int countFreeCells(Board board) {
        int count = 0;
        for (int i = 1; i <= board.getBoardSize() * board.getBoardSize(); i++) {
            if (board.isValidPosition(Integer.toString(i))) {
                count++;
            }
        }
        return count;
    }
}
