package tdd;

import java.util.Scanner;

public class Player {
    private final int id;
    private final String name;
    private final char marker;
    private final Scanner sc;
    private int numberOfWins;

    /**
     * Creates a new player with specified name, ID, and marker
     */
    public Player(String name, int id, char marker) {
        this.id = id;
        this.name = name;
        this.numberOfWins = 0;
        this.marker = marker;
        sc = new Scanner(System.in);
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public char getMarker() {
        return this.marker;
    }

    public Scanner getSC() {
        return this.sc;
    }

    public void closeSC() {
        this.sc.close();
    }

    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    /**
     * Increments win count when player wins a game
     */
    public void incrementNumberOfWins() {
        this.numberOfWins++;
    }

    public void resetNumberOfWins() {
        this.numberOfWins = 0;
    }

    public void move(Board board) {
        String movePos;

        while (true) {
            // Prompt player for move with valid range
            System.out.println("Player " + this.name + ", please enter your move. (enter a value from 1 - " +
                    board.getBoardSize() * board.getBoardSize() + ")");
            board.print();

            if (sc.hasNextLine()) {
                movePos = sc.nextLine();

                // Validate the move before accepting
                if (!board.isValidPosition(movePos)) {
                    System.out.println("Invalid move. Please try again.");
                } else {
                    break; // Valid move, exit loop
                }
            }
        }

        // Place the validated move on the board
        board.placeTheMove(this.marker, Integer.parseInt(movePos));
    }
}
