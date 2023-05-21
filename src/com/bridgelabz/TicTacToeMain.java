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
//        // Make moves and display the updated board
//        game.makeMove(1, 'X');
//        game.makeMove(5, 'X');
//        game.makeMove(2, 'X');
//        game.makeMove(9, 'O');
        game.displayBoard();

        // Check if the board is full
        boolean isFull = game.isBoardFull();
        System.out.println("Board is full: " + isFull);
    }
}