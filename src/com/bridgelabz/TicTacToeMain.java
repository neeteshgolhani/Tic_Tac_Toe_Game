package com.bridgelabz;

public class TicTacToeMain {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.choosePlayerSymbol();
        game.doToss();
        game.playGame();
    }
}