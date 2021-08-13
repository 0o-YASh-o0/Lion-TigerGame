package com.example.lionandtigergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player{

        ONE, TWO , NONE
    }
    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];

    boolean gameOver = false;

    private Button btnReset;

    private int counter;

//    private GridLayout gridView ;
    private androidx.gridlayout.widget.GridLayout gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter=0;

        for (int index =0 ; index<playerChoices.length;index++)
        {
            playerChoices[index] = Player.NONE;
        }

//        playerChoices[0] = Player.NONE;
//        playerChoices[1] = Player.NONE;
//        playerChoices[2] = Player.NONE;
//        playerChoices[3] = Player.NONE;
//        playerChoices[4] = Player.NONE;
//        playerChoices[5] = Player.NONE;
//        playerChoices[6] = Player.NONE;
//        playerChoices[7] = Player.NONE;
//        playerChoices[8] = Player.NONE;

        btnReset = findViewById(R.id.btnReset);

        gridView = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetThegame();
            }
        });
    }







    public  void  imageViewIsTapped(View imageView) {

        counter++;
        ImageView tappedImageView = (ImageView) imageView;
        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[tiTag] == Player.NONE  && gameOver == false) {
            tappedImageView.setTranslationX(-2000f);

            playerChoices[tiTag] = currentPlayer;
            int[][] winnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
                    {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

            if (currentPlayer == Player.ONE) {

                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {

                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.ONE;
            }

            tappedImageView.animate().translationXBy(2000f).alpha(1).rotation(3600).setDuration(800);
//        Toast.makeText(this,tappedImageView.getTag().toString(),Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColumns)
            //Checking one row at a time so 1D array is used 035
            {

                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                        && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                        && playerChoices[winnerColumns[0]] != Player.NONE) {
                //If player is playing at specific indexes as ment. in array
                //we're checking the current player and the boxes he ticks simultaneously
                //So if the player 1 ticks at indexes at 0,1,2 then we from the loop we're
                //checking that same player is ticking at those indexes
                //In the playerChoices[] 1d array gets a value of any of the winnerRowsColumns
                //array then the player wins
                btnReset.setVisibility(View.VISIBLE);
                gameOver=true;
                if (currentPlayer == Player.ONE) {
                    Toast.makeText(this, "Player 2 is the WINNER!", Toast.LENGTH_LONG).show();
                    
//                    AlertDisplay("Player two wins the game","What you want to do?");
                } else if (currentPlayer == Player.TWO) {

                    Toast.makeText(this, "Player 1 is the WINNER!", Toast.LENGTH_LONG).show();
                }
            }
//                else {
//                    Toast.makeText(this, "It's a Draw", Toast.LENGTH_LONG).show();
//                    resetThegame();
//                }
            }


        }
        if (counter == playerChoices.length) {
            btnReset.setVisibility(View.VISIBLE);
            gameOver = true;
            Toast.makeText(this, "It's a draw!", Toast.LENGTH_LONG).show();
            counter = 0;
        }

    }
    private void resetThegame(){

        for (int index=0 ; index<gridView.getChildCount() ; index ++){

            ImageView imageView = (ImageView) gridView.getChildAt(index);
            //returns image at that index
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);


        }

        btnReset.setVisibility(View.GONE);
        currentPlayer = Player.ONE;
        for (int index =0 ; index<playerChoices.length;index++)
        {
            playerChoices[index] = Player.NONE;
        }
//        playerChoices[0] = Player.NONE;
//        playerChoices[1] = Player.NONE;
//        playerChoices[2] = Player.NONE;
//        playerChoices[3] = Player.NONE;
//        playerChoices[4] = Player.NONE;
//        playerChoices[5] = Player.NONE;
//        playerChoices[6] = Player.NONE;
//        playerChoices[7] = Player.NONE;
//        playerChoices[8] = Player.NONE;

        gameOver = false;

    }
}



