import game.Board;
import java.util.InputMismatchException;
import java.util.Scanner;
import ai.AIPlayer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Connect 4!");

        int gameMode = selectGameMode(sc);

        AIPlayer aiPlayer = null;
        char player1Disc = 'R';
        char player2Disc = 'Y';
        String player1Name = "Player 1";
        String player2Name = "Player 2";
        int currPlayer = 0;

        switch (gameMode) {
            case 1:
                // Two Player mode
                System.out.println("Starting a game for two players...");
                player1Name = getPlayerName(sc, "Enter Player 1 name");
                player1Disc = getPlayerSymbol(sc, player1Name);
                player2Name = getPlayerName(sc, "Enter Player 2 name");
                player2Disc = (player1Disc == 'R') ? 'Y' : 'R';
                System.out.println(player2Name + " will play as " + player2Disc);
                break;
            case 2:
                // Single-player vs AI mode
                System.out.println("Starting a game against the AI...");
                player1Name = getPlayerName(sc, "Enter your name");
                player1Disc = getPlayerSymbol(sc, player1Name);
                player2Disc = (player1Disc == 'R') ? 'Y' : 'R';
                currPlayer = getFirstTurn(sc);
                aiPlayer = new AIPlayer(player2Disc, player1Disc);
                break;
        }

        Board board = new Board();
        boolean endGame = false;

        while (!endGame) {
            if (gameMode == 2 && currPlayer == 0) {    // AI's turn
                int aiMove = aiPlayer.getBestMove(board);
                if (board.addDisc(aiMove + 1, player2Disc)) {  // Adjust column index for board
                    System.out.println("AI (Player " + player2Disc + ") places a disc in column " + (aiMove + 1)); // Adjust column index for user display
                }
                if (board.checkWin(player2Disc)) {
                    endGame = true;
                    board.printBoard();  // Show final board
                    System.out.println("AI WINS!");
                } else if (board.isFull()) {
                    endGame = true;
                    board.printBoard();
                    System.out.println("The game is a draw!!");
                } else {
                    currPlayer = 1;  // Switch to human player
                }
            } else {   // Human player's turn
                board.printBoard();
                System.out.println("Player " + (currPlayer == 1 ? player1Name : player2Name) + ", choose a column (1-7): ");
                try {
                    int column = sc.nextInt();
                    if (board.addDisc(column, currPlayer == 1 ? player1Disc : player2Disc)) {
                        System.out.println("Adding " + (currPlayer == 1 ? player1Name : player2Name) +
                                " (Player " + (currPlayer == 1 ? player1Disc : player2Disc) + ") disc to column " + column);
                        if (board.checkWin(currPlayer == 1 ? player1Disc : player2Disc)) {
                            endGame = true;
                            board.printBoard();  // Show final board
                            System.out.println("Player " + (currPlayer == 1 ? player1Name : player2Name) + " WINS!");
                        } else if (board.isFull()) {
                            endGame = true;
                            board.printBoard();
                            System.out.println("The game is a draw!!");
                        } else {
                            currPlayer = (gameMode == 1) ? (currPlayer == 1 ? 2 : 1) : 0;
                        }
                    } else {
                        System.out.println("Column is full or invalid! Please choose another one");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number between 1 and 7");
                    sc.next();  // Clear invalid input
                }
            }
        }
        sc.close();
    }
    private static int selectGameMode(Scanner sc) {
        int gameMode = 0;
        while (true) {
            System.out.println("Select game mode:");
            System.out.println("1. Two Player");
            System.out.println("2. Single Player vs AI");
            try {
                gameMode = sc.nextInt();
                if (gameMode == 1 || gameMode == 2) {
                    break;
                } else {
                    System.out.println("Invalid selection! Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number (1 or 2).");
                sc.next();
            }
        }
        return gameMode;
    }

    private static char getPlayerSymbol(Scanner sc, String playerName) {
        char symbol;
        while (true) {
            System.out.print(playerName + ", choose your symbol ('R' or 'Y'): ");
            String input = sc.next().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'R' || input.charAt(0) == 'Y')) {
                symbol = input.charAt(0);
                break;
            } else {
                System.out.println("Invalid symbol. Please enter 'R' or 'Y'.");
            }
        }
        return symbol;
    }

    private static String getPlayerName(Scanner sc, String prompt) {
        String name;
        while (true) {
            System.out.print(prompt + ": ");
            name = sc.next();
            // Check if the name contains only letters and possibly spaces.
            if (name.matches("[a-zA-Z ]+")) {
                break; // Valid name, exit loop
            } else {
                System.out.println("Invalid name. Please enter a name without numbers.");
            }
        }
        return name;
    }

    private static int getFirstTurn(Scanner sc) {
        while (true) {
            System.out.print("Who should go first? (player/computer): ");
            String firstTurn = sc.next();
            if (firstTurn.equalsIgnoreCase("player")) {
                return 1;
            } else if (firstTurn.equalsIgnoreCase("computer")) {
                return 0;
            } else {
                System.out.println("Invalid selection! Please type 'player' or 'computer'.");
            }
        }
    }
}