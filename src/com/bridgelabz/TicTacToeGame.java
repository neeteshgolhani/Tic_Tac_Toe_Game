package com.bridgelabz;
import java.util.Random;
import java.util.Scanner;
public class TicTacToeGame {
    private char[] board;
    private char playerSymbol;
    private char computerSymbol;
    private boolean isPlayerTurn;

    public TicTacToeGame() {
        board = new char[10]; // Ignoring index 0 for user-friendliness
        initializeBoard();
        isPlayerTurn = true;
    }

    public void initializeBoard() {
        for (int i = 1; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    public void showBoard() {
        System.out.println("-------------");
        System.out.println("| " + board[1] + " | " + board[2] + " | " + board[3] + " |");
        System.out.println("-------------");
        System.out.println("| " + board[4] + " | " + board[5] + " | " + board[6] + " |");
        System.out.println("-------------");
        System.out.println("| " + board[7] + " | " + board[8] + " | " + board[9] + " |");
        System.out.println("-------------");
    }

    public void choosePlayerSymbol() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose your symbol (X/O): ");
        char symbol = scanner.nextLine().toUpperCase().charAt(0);
        while (symbol != 'X' && symbol != 'O') {
            System.out.print("Invalid symbol. Choose your symbol (X/O): ");
            symbol = scanner.nextLine().toUpperCase().charAt(0);
        }
        playerSymbol = symbol;
        computerSymbol = (playerSymbol == 'X') ? 'O' : 'X';
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public char getComputerSymbol() {
        return computerSymbol;
    }

    public void doToss() {
        Random random = new Random();
        int result = random.nextInt(2); // 0 for heads, 1 for tails

        if (result == 0) {
            isPlayerTurn = true;
            System.out.println("Player won the toss and starts first!");
        } else {
            isPlayerTurn = false;
            System.out.println("Computer won the toss and starts first!");
        }
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public boolean makePlayerMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your move (1-9): ");
        int position = scanner.nextInt();

        if (position < 1 || position > 9) {
            System.out.println("Invalid move. Please enter a position between 1 and 9.");
            return false;
        } else if (board[position] != ' ') {
            System.out.println("Invalid move. The selected position is not available.");
            return false;
        } else {
            makeMove(position, playerSymbol);
            System.out.println("Player moved to position " + position);
            return true;
        }
    }

    public void makeComputerMove() {
        // Check if the computer can win
        for (int i = 1; i < board.length; i++) {
            if (board[i] == ' ') {
                board[i] = computerSymbol;
                if (checkWin(computerSymbol)) {
                    System.out.println("Computer moved to position " + i);
                    return;
                }
                // Undo the move if it doesn't lead to a win
                board[i] = ' ';
            }
        }

        // Check if the player can win and block them
        for (int i = 1; i < board.length; i++) {
            if (board[i] == ' ') {
                board[i] = playerSymbol;
                if (checkWin(playerSymbol)) {
                    board[i] = computerSymbol;
                    System.out.println("Computer moved to position " + i);
                    return;
                }
                // Undo the move if it doesn't block the player from winning
                board[i] = ' ';
            }
        }
        // Check if a corner position is available
        int[] corners = {1, 3, 7, 9};
        for (int corner : corners) {
            if (board[corner] == ' ') {
                makeMove(corner, computerSymbol);
                System.out.println("Computer moved to position " + corner);
                return;
            }
        }

        // Check if the center position is available
        if (board[5] == ' ') {
            makeMove(5, computerSymbol);
            System.out.println("Computer moved to position 5");
            return;
        }

        // Check if any side position is available
        int[] sides = {2, 4, 6, 8};
        for (int side : sides) {
            if (board[side] == ' ') {
                makeMove(side, computerSymbol);
                System.out.println("Computer moved to position " + side);
                return;
            }
        }

        // If no winning move, blocking move, corner move, center move, or side move is found,
        // make a random move
        int position = getRandomAvailablePosition();
        makeMove(position, computerSymbol);
        System.out.println("Computer moved to position " + position);
    }

    private int getRandomAvailablePosition() {
        Random random = new Random();
        int position;
        do {
            position = random.nextInt(9) + 1;
        } while (board[position] != ' ');
        return position;
    }

    private void makeMove(int position, char symbol) {
        board[position] = symbol;
    }

    public boolean checkWin(char symbol) {
        // Check all winning combinations
        if ((board[1] == symbol && board[2] == symbol && board[3] == symbol) ||
                (board[4] == symbol && board[5] == symbol && board[6] == symbol) ||
                (board[7] == symbol && board[8] == symbol && board[9] == symbol) ||
                (board[1] == symbol && board[4] == symbol && board[7] == symbol) ||
                (board[2] == symbol && board[5] == symbol && board[8] == symbol) ||
                (board[3] == symbol && board[6] == symbol && board[9] == symbol) ||
                (board[1] == symbol && board[5] == symbol && board[9] == symbol) ||
                (board[3] == symbol && board[5] == symbol && board[7] == symbol)) {
            return true; // If any winning combination matches, return true
        }
        return false; // No winning combination found
    }

    public boolean checkDraw() {
        // Check if any empty position exists
        for (int i = 1; i < board.length; i++) {
            if (board[i] == ' ') {
                return false; // Empty position found, game is not a draw
            }
        }
        return true; // All positions are filled, game is a draw
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        do {
            initializeBoard();
            doToss();

            while (true) {
                showBoard();

                if (isPlayerTurn()) {
                    // Player's turn
                    while (!makePlayerMove()) {
                        // Keep asking for a valid player move
                    }
                    if (checkWin(playerSymbol)) {
                        showBoard();
                        System.out.println("Congratulations! You won!");
                        break; // Exit the game loop
                    } else if (checkDraw()) {
                        showBoard();
                        System.out.println("It's a draw!");
                        break; // Exit the game loop
                    }
                } else {
                    // Computer's turn
                    makeComputerMove();
                    if (checkWin(computerSymbol)) {
                        showBoard();
                        System.out.println("You lost! Better luck next time.");
                        break; // Exit the game loop
                    } else if (checkDraw()) {
                        showBoard();
                        System.out.println("It's a draw!");
                        break; // Exit the game loop
                    }
                }

                // Switch turns
                isPlayerTurn = !isPlayerTurn;
            }

            // Ask if the player wants to play another game
            System.out.print("Do you want to play another game? (Y/N): ");
            String playAgainInput = scanner.nextLine().toUpperCase();
            if (!playAgainInput.equals("Y")) {
                break; // Exit the game loop
            }
        } while (true);
            System.out.println("Thank you for playing!");
        }
    }
}