package tdd;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPlayers = -1;

        // Prompt the user to enter number of players (between 0 and 2)
        while (true) {
            System.out.println("How many players [0-2]?");
            String choice = scanner.nextLine();
            try {
                numberOfPlayers = Integer.parseInt(choice);
                if (numberOfPlayers >= 0 && numberOfPlayers <= 2) {
                    break; // Valid input
                } else {
                    System.out.println("Please enter a number between 0 and 2"); // Out of range
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 0 and 2"); // Not a number
            }
        }

        Player player1;
        Player player2;

        // Create players according to the user's selection
        switch (numberOfPlayers) {
            case 0:
                // Auto vs Auto
                player1 = new AutoPlayer("PLAYER-X", 1, 'X');
                player2 = new AutoPlayer("PLAYER-O", 2, 'O');
                break;
            case 1:
                // Human vs Auto
                player1 = new Player("PLAYER-X", 1, 'X');
                player2 = new AutoPlayer("PLAYER-O", 2, 'O');
                break;
            case 2:
            default:
                // Human vs Human (default)
                player1 = new Player("PLAYER-X", 1, 'X');
                player2 = new Player("PLAYER-O", 2, 'O');
        }

        // Start the game with the selected players
        TicTacToe game = new TicTacToe(player1, player2);
        game.play();
    }
}
