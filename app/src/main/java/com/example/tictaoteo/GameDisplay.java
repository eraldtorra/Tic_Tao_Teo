package com.example.tictaoteo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    private TicTacToeBoard ticTacToeBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);

        Button playAgainButton = findViewById(R.id.button3);
        Button homeButton = findViewById(R.id.button4);
        TextView playerTurn = findViewById(R.id.textView5);

        playAgainButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);

        String[] playerNames = getIntent().getStringArrayExtra("PLAYER_NAMES");

        // not give the player names to the game logic
        if (playerNames[0].equals("") && playerNames[1].equals("")) {
            playerNames = new String[]{"Player 1", "Player 2"};
        }else if (playerNames[0].equals("")) {
            playerNames[0] = "Player 1";
        }else if (playerNames[1].equals("")) {
            playerNames[1] = "Player 2";
        }

        if (playerNames != null) {
            playerTurn.setText(playerNames[0] + "'s turn");
        }

        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);

        ticTacToeBoard.setUpGame(playAgainButton,homeButton,playerTurn,playerNames);
    }

    public void playAgainButtonClick(View view) {
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();
    }

    public void homeButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}