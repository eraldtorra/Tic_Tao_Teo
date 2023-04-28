package com.example.tictaoteo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayerSetup extends AppCompatActivity {

    private EditText player1Name;
    private EditText player2Name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_setup);

        player1Name = findViewById(R.id.player1Name);
        player2Name = findViewById(R.id.player2Name);
    }



    public void submitButtonClicked(View view) {
        String player1 = player1Name.getText().toString();
        String player2 = player2Name.getText().toString();

        Intent intent = new Intent(this, GameDisplay.class);
        intent.putExtra("PLAYER_NAMES", new String[] {player1, player2});
        startActivity(intent);
    }
}