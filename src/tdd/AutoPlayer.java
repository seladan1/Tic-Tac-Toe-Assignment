package tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoPlayer extends Player {
    private final Random random = new Random(); // Random number generator for move selection

    /**
     * Creates a new automated player
     */
    public AutoPlayer(String name, int id, char marker) {
        super(name, id, marker);
    }

    @Override
    public void move(Board board) {
        List<Integer> availableSpots = new ArrayList<>();
        int size = board.getBoardSize();

        // Find all available (empty) positions on the board
        for (int i = 1; i <= size * size; i++) {
            if (board.isValidPosition(Integer.toString(i))) {
                availableSpots.add(i);
            }
        }

        // If no moves available, return (game should be over)
        if (availableSpots.isEmpty()) {
            return;
        }

        // Select a random move from available positions
        int move = availableSpots.get(random.nextInt(availableSpots.size()));
        board.placeTheMove(getMarker(), move);

        // Display the move to match human player output format
        System.out.println("Player " + this.getName() + ", please enter your move. (enter a value from 1 - " +
                board.getBoardSize() * board.getBoardSize() + ")");
        board.print();
        System.out.println(move);
    }
}
