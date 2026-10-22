package tdd;

import java.util.Scanner;

final public class TicTacToe {
    private static Scanner sc;
    private Player player1 = new Player("PLAYER-X", 1, 'X');
    private Player player2 = new Player("PLAYER-O", 2, 'O');
    private Board board;

    // Constructor for standard two-human-player game
    public TicTacToe() {
        sc = new Scanner(System.in);
    }

    // Constructor allowing injection of custom Player objects (for example, AutoPlayer)
    public TicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        sc = new Scanner(System.in);
    }

    // Main game loop
    public void play() {
        Player currentPlayer = this.player1;
        // Ask the user if they want to play
        if (!this.playAgain()) {
            this.gameOver();
            return;
        }

        // Game round loop
        while (true) {
            currentPlayer.move(this.board);
            // Check for win
            if (this.board.checkWin(currentPlayer.getMarker())) {
                this.handleWinner(currentPlayer);
                if (!this.playAgain()) {
                    this.gameOver();
                    return;
                }
            }
            // Check for tie
            else if(this.board.isFull()) {
                System.out.println("The board is full. It's a tie!");
                if (!this.playAgain()) {
                    this.gameOver();
                    return;
                } else {
                    continue; // Start a new round
                }
            }

            // Switch turns
            currentPlayer = currentPlayer == this.player1 ? this.player2 : this.player1;
        }
    }

    // Ends the game and prints the final result
    private void gameOver() {
        this.printResults();
        sc.close();
        this.player1.closeSC();
        this.player2.closeSC();
    }

    // Updates winner's score and prints a win message
    void handleWinner(Player winner) {
        winner.incrementNumberOfWins();
        System.out.println(winner.getName() + " has won this round!");
    }

    // Displays the initial prompt for playing
    private void welcome() {
        System.out.println("Hit \"y/Y\" to start a new game. Or hit any other key to exit.");
    }

    // Prompts the user to enter a valid board size (3–10)
    private int getBoardSize() {
        while (true) {
            System.out.print("Please enter your preferred SIZE of the board");
            System.out.println(" (from 3 to 10. 3 -> 3x3; 4 -> 4x4; 10 -> 10x10, etc): ");

            if (sc.hasNextLine()) {
                String userInput = sc.nextLine();
                if(this.verifyBoardSize(userInput)) {
                    return Integer.parseInt(userInput);
                }
            }
        }
    }

    // Validates the user's input for board size
    boolean verifyBoardSize(String boardSize) {
        try {
            int size = Integer.parseInt(boardSize);
            return size >= 3 && size <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Prompts the user to start a new game or quit
    private boolean playAgain() {
        this.welcome();
        sc = new Scanner((System.in));
        String userDecision = sc.nextLine();

        if (userDecision.equalsIgnoreCase("Y")) {
            int boardSize = this.getBoardSize();
            this.board = new Board(boardSize);
            return true;
        }

        return false;
    }

    // Prints the final win count and announces the winner
    public void printResults() {
        System.out.println("Player " + this.player1.getName() + " has won: " + this.player1.getNumberOfWins() + " time(s).");
        System.out.println("Player " + this.player2.getName() + " has won: " + this.player2.getNumberOfWins() + " time(s).");

        if (this.player1.getNumberOfWins() == this.player2.getNumberOfWins()) {
            System.out.println("Its a tie!");
        } else {
            String winner = this.player1.getNumberOfWins() > this.player2.getNumberOfWins() ? this.player1.getName() : this.player2.getName();
            System.out.println("The final winner is: " + winner + "!!!");
        }

        System.out.println();
    }
}
