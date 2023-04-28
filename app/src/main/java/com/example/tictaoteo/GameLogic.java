package com.example.tictaoteo;

import android.widget.Button;
import android.widget.TextView;

public class GameLogic  {

    private int[][] gameBoard = new int[3][3];
    private int player = 1;

    private Button playAgainButton;
    private Button homeButton;
    private TextView playerTurn;

    private String[] playerNames = {"Player 1", "Player 2"};

    GameLogic() {
      gameBoard= new int[3][3];
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          gameBoard[i][j] = 0;
        }
      }
    }

    public boolean updateGameBoard(int row, int col) {
        if (gameBoard[row - 1][col - 1] == 0) {
            gameBoard[row - 1][col - 1] = player;

            if (player == 1) {
                playerTurn.setText(playerNames[1] + "'s turn");
            } else {
                playerTurn.setText(playerNames[0] + "'s turn");
            }
            return true;

        } else {
            return false;
        }
    }

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = 0;
            }
        }

        player = 1;

        playAgainButton.setVisibility(Button.GONE);
        homeButton.setVisibility(Button.GONE);

        playerTurn.setText(playerNames[0] + "'s turn");
    }
    public boolean winnerCheck(){

        boolean isWinner = false;

        // checking for horizontal win
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] &&
                    gameBoard[i][0] != 0) {

                isWinner = true;
            }
        }

        // checking for vertical win
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] == gameBoard[1][i] && gameBoard[2][i] == gameBoard[0][i]
                    &&gameBoard[0][i]!=0) {

                isWinner = true;

            }
        }

        // checking for diagonal win
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2]
        && gameBoard[0][0] != 0) {

            isWinner = true;

        }

        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2]
        && gameBoard[2][0] != 0) {

            isWinner = true;

        }
        int boardFilled = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] != 0) {
                    boardFilled++;
                }
            }
        }

        if (isWinner) {
            playAgainButton.setVisibility(Button.VISIBLE);
            homeButton.setVisibility(Button.VISIBLE);
            playerTurn.setText("Winner is " + playerNames[player-1]);
            return true;
        }else
            if (boardFilled == 9){
                playAgainButton.setVisibility(Button.VISIBLE);
                homeButton.setVisibility(Button.VISIBLE);
                playerTurn.setText("Draw");
                return true;
            }else {
                return false;
            }

    }

    public void setPlayAgainButton(Button playAgainButton) {
        this.playAgainButton = playAgainButton;
    }

    public void setHomeButton(Button homeButton) {
        this.homeButton = homeButton;
    }
    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayerName(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public int[][] getGameBoard() {
      return gameBoard;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
