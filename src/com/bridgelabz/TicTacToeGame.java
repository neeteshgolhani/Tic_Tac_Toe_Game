package com.bridgelabz;

public class TicTacToeGame {
    private char[] board;

    public TicTacToeGame() {
        board = new char[10];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 1; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    public void displayBoard() {
        // Display the tic-tac-toe board
        System.out.println(" " + board[1] + " | " + board[2] + " | " + board[3] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + board[4] + " | " + board[5] + " | " + board[6] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + board[7] + " | " + board[8] + " | " + board[9] + " ");
    }

    public boolean isBoardFull() {
        for (int i = 1; i < board.length; i++) {
            // If any empty space is found, return false
            if (board[i] == ' ') {
                return false;
            }
        }
        // If no empty spaces are found, return true
        return true;
    }

    public boolean makeMove(int position, char symbol) {
        if (position < 1 || position > 9 || board[position] != ' ') {
            // Invalid move, return false
            return false;
        }
        // Assign the symbol to the specified position on the board
        board[position] = symbol;
        // Move successful, return true
        return true;
    }
}
