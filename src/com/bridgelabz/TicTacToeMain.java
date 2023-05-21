package com.bridgelabz;

public class TicTacToeMain {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.displayBoard();

        game.choosePlayerSymbol();

        char playerSymbol = game.getPlayerSymbol();
        char computerSymbol = game.getComputerSymbol();
        System.out.println("Player symbol: " + playerSymbol);
        System.out.println("Computer symbol: " + computerSymbol);

        // Perform toss
        game.doToss();

        // Display the board
        game.showBoard();

        // Player or computer makes a move based on the toss result
        if (game.isPlayerTurn()) {
            game.makePlayerMove();
        } else {
            // Make computer's move
            // Implement the logic for the computer's move
        }

        // Display the updated board
        game.showBoard();

        // Check if the board is full
        boolean isFull = game.isBoardFull();
        System.out.println("Board is full: " + isFull);
    }
}