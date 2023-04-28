package com.example.tictaoteo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {

    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winningLineColor;

    private boolean winningLine = false;

    private final Paint paint = new Paint();

    private int cellSize = getWidth() / 3;

    private GameLogic game;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new GameLogic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TicTacToeBoard, 0, 0);

        try {
            boardColor = a.getColor(R.styleable.TicTacToeBoard_boardColor, 0);
            XColor = a.getColor(R.styleable.TicTacToeBoard_XColor, 0);
            OColor = a.getColor(R.styleable.TicTacToeBoard_OColor, 0);
            winningLineColor = a.getColor(R.styleable.TicTacToeBoard_winningLineColor, 0);

        }finally {
            a.recycle();
        }

    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension / 3;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       paint.setStyle(Paint.Style.STROKE);
       paint.setAntiAlias(true);

       drawGameBoard(canvas);

       drawMarkers(canvas);


    }

    private void drawMarkers(Canvas canvas) {

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (game.getGameBoard() [r][c] != 0) {
                    if (game.getGameBoard() [r][c] == 1) {
                        drawX(canvas, r, c);
                    } else {
                        drawO(canvas, r, c);
                    }
                }
            }
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y / cellSize);
            int col = (int) Math.ceil(x / cellSize);

            if (!winningLine) {
                if (game.updateGameBoard(row, col)) {
                    invalidate();

                    if (game.winnerCheck()) {
                        winningLine = true;
                        invalidate();
                    }

                    // updating the player turn
                    if (game.getPlayer() % 2 == 0) {
                        game.setPlayer(game.getPlayer() - 1);
                    } else {
                        game.setPlayer(game.getPlayer() + 1);
                    }

                }
            }

            invalidate();

            return true;
        }
        return false;
    }

    private void drawGameBoard(Canvas canvas) {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);

        for (int i = 0; i < 3; i++) {
            canvas.drawLine(cellSize * i, 0, cellSize * i, canvas.getWidth(), paint);
        }

        for (int r=1; r<3; r++) {
            canvas.drawLine(0, cellSize * r, canvas.getWidth(), cellSize * r, paint);
        }
    }

    private void drawX(Canvas canvas, int row, int col) {
        paint.setColor(XColor);


        canvas.drawLine(cellSize * (col+1)-cellSize*0.2f,
                cellSize * row+cellSize*0.2f,
                cellSize * col+cellSize*0.2f,
                cellSize * (row + 1)-cellSize*0.2f,
                paint);

        canvas.drawLine(cellSize * col+cellSize*0.2f,
                cellSize * row+cellSize*0.2f,
                cellSize * (col + 1)-cellSize*0.2f,
                cellSize * (row + 1)-cellSize*0.2f,
                paint);
    }

    private void drawO(Canvas canvas, int row, int col) {
        paint.setColor(OColor);

        canvas.drawOval(col*cellSize+cellSize*0.2f,
                row*cellSize+cellSize*0.2f,
                (col*cellSize+cellSize)-cellSize*0.2f,
                (row*cellSize+cellSize)-cellSize*0.2f,
                paint);

    }

    public void setUpGame(Button playAgain, Button home, TextView playerDisplay, String[] name) {
        game.setPlayAgainButton(playAgain);
        game.setHomeButton(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerName(name);
    }

    public void resetGame() {
        game.resetGame();
       winningLine = false;

    }


}
